package com.example.bfhl.service;

import com.example.bfhl.dto.RequestDto;
import com.example.bfhl.dto.ResponseDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class BfhlServiceImpl implements BfhlService {

    @Override
    public ResponseDto processData(RequestDto request, String requestId) {
        long startTime = System.currentTimeMillis();
        ResponseDto response = new ResponseDto();
        response.setRequestId(requestId);
        response.setSuccess(true);

        List<String> rawData = request.getData();
        int totalReceived = rawData == null ? 0 : rawData.size();

        List<String> validData = new ArrayList<>();
        int invalidCount = 0;
        boolean hasDuplicates = false;
        Set<String> uniqueTracker = new HashSet<>();

        // 1. Filter out null/empty and check for duplicates
        if (rawData != null) {
            for (String item : rawData) {
                if (item == null || item.trim().isEmpty()) {
                    invalidCount++;
                } else {
                    if (!uniqueTracker.add(item)) {
                        hasDuplicates = true;
                    } else {
                        validData.add(item);
                    }
                }
            }
        }

        response.setContainsDuplicates(hasDuplicates);
        response.setUniqueElementCount(validData.size());

        ResponseDto.SummaryDto summary = new ResponseDto.SummaryDto();
        summary.setTotalElementsReceived(totalReceived);
        summary.setValidElementsProcessed(validData.size());
        summary.setInvalidElementsIgnored(invalidCount);
        response.setSummary(summary);

        // Trackers
        List<String> alphabets = new ArrayList<>();
        List<String> specialChars = new ArrayList<>();
        List<BigDecimal> numbersList = new ArrayList<>();
        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();

        Pattern numPattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        Pattern alphaPattern = Pattern.compile("[a-zA-Z]+");
        Pattern specialPattern = Pattern.compile("[^a-zA-Z0-9.\\-\\s]");

        // 2. Extract Data
        for (String item : validData) {
            // Extract numbers
            Matcher numMatcher = numPattern.matcher(item);
            while (numMatcher.find()) {
                String numStr = numMatcher.group();
                try {
                    BigDecimal num = new BigDecimal(numStr);
                    numbersList.add(num);
                    // Check odd/even only for integers
                    if (num.scale() <= 0) {
                        long val = num.longValue();
                        if (val % 2 == 0) evenNumbers.add(numStr);
                        else oddNumbers.add(numStr);
                    }
                } catch (Exception ignored) {}
            }

            // Extract alphabets
            Matcher alphaMatcher = alphaPattern.matcher(item);
            while (alphaMatcher.find()) {
                alphabets.add(alphaMatcher.group().toUpperCase());
            }

            // Extract special characters
            Matcher specialMatcher = specialPattern.matcher(item);
            while (specialMatcher.find()) {
                specialChars.add(specialMatcher.group());
            }
        }

        // 3. Process Numbers
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal min = null;
        BigDecimal max = null;
        
        for (BigDecimal num : numbersList) {
            sum = sum.add(num);
            if (min == null || num.compareTo(min) < 0) min = num;
            if (max == null || num.compareTo(max) > 0) max = num;
        }

        List<String> sortedNumbers = numbersList.stream()
                .sorted()
                .map(BigDecimal::stripTrailingZeros)
                .map(BigDecimal::toPlainString)
                .collect(Collectors.toList());

        response.setSum(numbersList.isEmpty() ? "0" : sum.stripTrailingZeros().toPlainString());
        response.setSmallestNumber(min == null ? null : min.stripTrailingZeros().toPlainString());
        response.setLargestNumber(max == null ? null : max.stripTrailingZeros().toPlainString());
        response.setNumberCount(numbersList.size());
        response.setOddNumbers(oddNumbers);
        response.setEvenNumbers(evenNumbers);
        response.setSortedNumbers(sortedNumbers);

        // 4. Process Alphabets
        int vowels = 0;
        int consonants = 0;
        Map<String, Integer> freqMap = new HashMap<>();
        String longest = null;
        String shortest = null;

        for (String word : alphabets) {
            if (longest == null || word.length() > longest.length()) longest = word;
            if (shortest == null || word.length() < shortest.length()) shortest = word;

            for (char c : word.toCharArray()) {
                String charStr = String.valueOf(c).toUpperCase();
                freqMap.put(charStr, freqMap.getOrDefault(charStr, 0) + 1);
                
                if ("AEIOU".indexOf(c) != -1) vowels++;
                else if (Character.isLetter(c)) consonants++;
            }
        }

        response.setAlphabets(alphabets);
        response.setAlphabetCount(alphabets.size());
        response.setVowelCount(vowels);
        response.setConsonantCount(consonants);
        response.setAlphabetFrequency(freqMap);
        response.setLongestAlphabeticValue(longest);
        response.setShortestAlphabeticValue(shortest);

        // 5. Process Special Characters
        response.setSpecialCharacters(specialChars);
        response.setSpecialCharacterCount(specialChars.size());

        // Finalize Time
        long endTime = System.currentTimeMillis();
        response.setProcessingTimeMs(endTime - startTime);

        return response;
    }
}