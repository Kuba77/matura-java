package org.matura.rok2015.liceum.zadanie4;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    private static int moreZeros = 0;
    private static int divisibleByTwo = 0;
    private static int divisibleByEight = 0;

    private static String biggest = "";
    private static String smallest = "";
    private static int biggestIndex = 0;
    private static int smallestIndex = 0;

    private static Path filePath = Paths.get("res/liczby.txt");

    public static void main(String[] args) throws IOException {
        scanFile();
        printResults();
    }

    private static void scanFile() throws IOException {
        Scanner scanner = new Scanner(filePath);

        int currentIndex = 0;
        while (scanner.hasNext()) {
            currentIndex++;
            String current = scanner.next();
            parseLine(currentIndex, current);
        }
    }

    private static void parseLine(int currentIndex, String current) {
        int zeros = 0;
        boolean hasFour = true;
        boolean hasTwo = true;
        boolean checkBiggest = true;
        boolean checkSmallest = true;

        int isBiggestR = isBiggest(current);
        if (isBiggestR == 1) {
            biggest = current;
            biggestIndex = currentIndex;
            checkBiggest = false;
        }
        else if (isBiggestR == -1) {
            checkBiggest = false;
        }

        int isSmallestR = isSmallest(current);
        if (isSmallestR == 1) {
            smallest = current;
            smallestIndex = currentIndex;
            checkSmallest = false;
        }
        else if (isSmallestR == -1) {
            checkSmallest = false;
        }

        for (int x = 0; x < current.length(); x++) {
            char character = current.charAt(x);

            if (checkBiggest) {
                int compareBitsBiggestR = compareBitsBiggest(x, character);
                if (compareBitsBiggestR == 1) {
                    biggest = current;
                    biggestIndex = currentIndex;
                    checkBiggest = false;
                } else if (compareBitsBiggestR == -1) {
                    checkBiggest = false;
                }
            }
            if (checkSmallest) {
                int compareBitsSmallestR = compareBitsSmallest(x, character);
                if (compareBitsSmallestR == 1) {
                    smallest = current;
                    smallestIndex = currentIndex;
                    checkSmallest = false;
                } else if (compareBitsSmallestR == -1) {
                    checkSmallest = false;
                }
            }

            if (character == '0') {
                zeros++;
                if (x == current.length() - 3) {
                    hasFour = false;
                }
                if (x == current.length() - 2) {
                    hasTwo = false;
                }
                if (x == current.length() - 1) {
                    divisibleByTwo++;
                    if (!hasFour && !hasTwo) {
                        divisibleByEight++;
                    }
                }
            }
        }

        if (zeros * 2 > current.length()) {
            moreZeros++;
        }
    }

    private static int isBiggest(String current) {
        if (current.length() > biggest.length()) {
            return 1;
        } else if (current.length() < biggest.length()) {
            return -1;
        }
        return 0;
    }

    private static int isSmallest(String current) {
        if (smallestIndex == 0 || current.length() < smallest.length()) {
            return 1;
        } else if (current.length() > smallest.length()) {
            return -1;
        }
        return 0;
    }

    private static int compareBitsBiggest(int x, char character) {
        if (biggest.charAt(x) == '0' && character == '1') {
            return 1;
        }
        else if (biggest.charAt(x) == '1' && character == '0') {
            return -1;
        }
        return 0;
    }

    private static int compareBitsSmallest(int x, char character) {
        if (smallest.charAt(x) == '1' && character == '0') {
            return 1;
        }
        else if (smallest.charAt(x) == '0' && character == '1') {
            return -1;
        }
        return 0;
    }

    private static void printResults() {
        System.out.println("4.1 " + moreZeros);
        System.out.println("4.2 " + divisibleByTwo + ", " + divisibleByEight);
        System.out.println("4.3 " + biggestIndex + ", " + smallestIndex);
    }
}
