package com.example.bfhl.dto;

import java.util.List;
import java.util.Map;

public class ResponseDto {
    private boolean isSuccess;
    private String requestId;
    private List<String> oddNumbers;
    private List<String> evenNumbers;
    private List<String> alphabets;
    private List<String> specialCharacters;
    private String sum;
    private String largestNumber;
    private String smallestNumber;
    private int alphabetCount;
    private int numberCount;
    private int specialCharacterCount;
    private boolean containsDuplicates;
    private int uniqueElementCount;
    private Map<String, Integer> alphabetFrequency;
    private List<String> sortedNumbers;
    private int vowelCount;
    private int consonantCount;
    private String longestAlphabeticValue;
    private String shortestAlphabeticValue;
    private long processingTimeMs;
    private SummaryDto summary;

    // Getters and Setters
    public boolean isSuccess() { return isSuccess; }
    public void setSuccess(boolean success) { isSuccess = success; }
    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }
    public List<String> getOddNumbers() { return oddNumbers; }
    public void setOddNumbers(List<String> oddNumbers) { this.oddNumbers = oddNumbers; }
    public List<String> getEvenNumbers() { return evenNumbers; }
    public void setEvenNumbers(List<String> evenNumbers) { this.evenNumbers = evenNumbers; }
    public List<String> getAlphabets() { return alphabets; }
    public void setAlphabets(List<String> alphabets) { this.alphabets = alphabets; }
    public List<String> getSpecialCharacters() { return specialCharacters; }
    public void setSpecialCharacters(List<String> specialCharacters) { this.specialCharacters = specialCharacters; }
    public String getSum() { return sum; }
    public void setSum(String sum) { this.sum = sum; }
    public String getLargestNumber() { return largestNumber; }
    public void setLargestNumber(String largestNumber) { this.largestNumber = largestNumber; }
    public String getSmallestNumber() { return smallestNumber; }
    public void setSmallestNumber(String smallestNumber) { this.smallestNumber = smallestNumber; }
    public int getAlphabetCount() { return alphabetCount; }
    public void setAlphabetCount(int alphabetCount) { this.alphabetCount = alphabetCount; }
    public int getNumberCount() { return numberCount; }
    public void setNumberCount(int numberCount) { this.numberCount = numberCount; }
    public int getSpecialCharacterCount() { return specialCharacterCount; }
    public void setSpecialCharacterCount(int specialCharacterCount) { this.specialCharacterCount = specialCharacterCount; }
    public boolean isContainsDuplicates() { return containsDuplicates; }
    public void setContainsDuplicates(boolean containsDuplicates) { this.containsDuplicates = containsDuplicates; }
    public int getUniqueElementCount() { return uniqueElementCount; }
    public void setUniqueElementCount(int uniqueElementCount) { this.uniqueElementCount = uniqueElementCount; }
    public Map<String, Integer> getAlphabetFrequency() { return alphabetFrequency; }
    public void setAlphabetFrequency(Map<String, Integer> alphabetFrequency) { this.alphabetFrequency = alphabetFrequency; }
    public List<String> getSortedNumbers() { return sortedNumbers; }
    public void setSortedNumbers(List<String> sortedNumbers) { this.sortedNumbers = sortedNumbers; }
    public int getVowelCount() { return vowelCount; }
    public void setVowelCount(int vowelCount) { this.vowelCount = vowelCount; }
    public int getConsonantCount() { return consonantCount; }
    public void setConsonantCount(int consonantCount) { this.consonantCount = consonantCount; }
    public String getLongestAlphabeticValue() { return longestAlphabeticValue; }
    public void setLongestAlphabeticValue(String longestAlphabeticValue) { this.longestAlphabeticValue = longestAlphabeticValue; }
    public String getShortestAlphabeticValue() { return shortestAlphabeticValue; }
    public void setShortestAlphabeticValue(String shortestAlphabeticValue) { this.shortestAlphabeticValue = shortestAlphabeticValue; }
    public long getProcessingTimeMs() { return processingTimeMs; }
    public void setProcessingTimeMs(long processingTimeMs) { this.processingTimeMs = processingTimeMs; }
    public SummaryDto getSummary() { return summary; }
    public void setSummary(SummaryDto summary) { this.summary = summary; }

    public static class SummaryDto {
        private int totalElementsReceived;
        private int validElementsProcessed;
        private int invalidElementsIgnored;

        public int getTotalElementsReceived() { return totalElementsReceived; }
        public void setTotalElementsReceived(int totalElementsReceived) { this.totalElementsReceived = totalElementsReceived; }
        public int getValidElementsProcessed() { return validElementsProcessed; }
        public void setValidElementsProcessed(int validElementsProcessed) { this.validElementsProcessed = validElementsProcessed; }
        public int getInvalidElementsIgnored() { return invalidElementsIgnored; }
        public void setInvalidElementsIgnored(int invalidElementsIgnored) { this.invalidElementsIgnored = invalidElementsIgnored; }
    }
}