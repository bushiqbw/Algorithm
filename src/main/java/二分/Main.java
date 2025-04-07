package 二分;

import java.util.Scanner;

import java.util.*;



public class Solution {

    private static class Results {
        public String set_name;
        public String[] selected_cards;

        public Results(String set_name, String[] selected_cards) {
            this.set_name = set_name;
            this.selected_cards = selected_cards;
        }
    }

    public Results solution(String[] cards) {
        // Check each group in order of strength, starting from the strongest
        Results res = checkSixthGroup(cards);
        if (res != null) return res;

        res = checkFifthGroup(cards);
        if (res != null) return res;

        res = checkFourthGroup(cards);
        if (res != null) return res;

        res = checkThirdGroup(cards);
        if (res != null) return res;

        res = checkSecondGroup(cards);
        if (res != null) return res;

        return checkFirstGroup(cards);
    }

    private Results checkSixthGroup(String[] cards) {
        int[] countRank = new int[13];
        for (String card : cards) {
            countRank[getRankValue(card)]++;
        }

        int maxTripleRank = -1;
        int maxPairRank = -1;

        for (int r = 12; r >= 0; r--) {
            if (countRank[r] >= 3) {
                int currentTriple = r;
                int currentPair = -1;

                // Check for pair within the same rank
                if (countRank[r] >= 5) {
                    currentPair = r;
                }

                // Check for pair in other ranks
                for (int p = 12; p >= 0; p--) {
                    if (p != r && countRank[p] >= 2) {
                        currentPair = p;
                        break;
                    }
                }

                if (currentPair != -1) {
                    if (currentTriple > maxTripleRank || (currentTriple == maxTripleRank && currentPair > maxPairRank)) {
                        maxTripleRank = currentTriple;
                        maxPairRank = currentPair;
                    }
                }
            }
        }

        if (maxTripleRank == -1) return null;

        // Collect cards for triple and pair
        List<String> tripleCandidates = new ArrayList<>();
        List<String> pairCandidates = new ArrayList<>();
        for (String card : cards) {
            int rank = getRankValue(card);
            if (rank == maxTripleRank) {
                tripleCandidates.add(card);
            } else if (rank == maxPairRank) {
                pairCandidates.add(card);
            }
        }

        List<String> selected = new ArrayList<>();
        if (maxTripleRank == maxPairRank) {
            if (tripleCandidates.size() >= 5) {
                selected.addAll(tripleCandidates.subList(0, 5));
            } else {
                return null;
            }
        } else {
            if (tripleCandidates.size() >= 3 && pairCandidates.size() >= 2) {
                selected.addAll(tripleCandidates.subList(0, 3));
                selected.addAll(pairCandidates.subList(0, 2));
            } else {
                return null;
            }
        }

        return new Results("a triple and a pair", selected.toArray(new String[0]));
    }

    private Results checkFifthGroup(String[] cards) {
        Map<Character, List<String>> suitMap = new HashMap<>();
        for (String card : cards) {
            char suit = card.charAt(card.length() - 1);
            suitMap.computeIfAbsent(suit, k -> new ArrayList<>()).add(card);
        }

        char[] suitsOrder = {'S', 'H', 'D', 'C'};
        for (char suit : suitsOrder) {
            List<String> suited = suitMap.get(suit);
            if (suited != null && suited.size() >= 5) {
                List<String> selected = suited.subList(0, 5);
                return new Results("suit", selected.toArray(new String[0]));
            }
        }

        return null;
    }

    private Results checkFourthGroup(String[] cards) {
        Set<Integer> uniqueRanks = new HashSet<>();
        for (String card : cards) {
            uniqueRanks.add(getRankValue(card));
        }

        List<Integer> sortedRanks = new ArrayList<>(uniqueRanks);
        Collections.sort(sortedRanks);

        int maxStart = -1;
        for (int i = 0; i <= sortedRanks.size() - 5; i++) {
            int start = sortedRanks.get(i);
            if (sortedRanks.get(i + 4) == start + 4) {
                if (start > maxStart) {
                    maxStart = start;
                }
            }
        }

        if (maxStart == -1) return null;

        List<Integer> neededRanks = new ArrayList<>();
        for (int r = maxStart; r <= maxStart + 4; r++) {
            neededRanks.add(r);
        }

        List<String> selected = new ArrayList<>();
        Set<Integer> added = new HashSet<>();
        for (String card : cards) {
            int rank = getRankValue(card);
            if (neededRanks.contains(rank) && !added.contains(rank)) {
                selected.add(card);
                added.add(rank);
                if (selected.size() == 5) break;
            }
        }

        return selected.size() == 5 ? new Results("five in a row", selected.toArray(new String[0])) : null;
    }

    private Results checkThirdGroup(String[] cards) {
        int[] countRank = new int[13];
        for (String card : cards) {
            countRank[getRankValue(card)]++;
        }

        int maxRank = -1;
        for (int r = 12; r >= 0; r--) {
            if (countRank[r] >= 3) {
                maxRank = r;
                break;
            }
        }

        if (maxRank == -1) return null;

        List<String> candidates = new ArrayList<>();
        for (String card : cards) {
            if (getRankValue(card) == maxRank) {
                candidates.add(card);
            }
        }

        return new Results("triple", candidates.subList(0, 3).toArray(new String[0]));
    }

    private Results checkSecondGroup(String[] cards) {
        int[] countRank = new int[13];
        for (String card : cards) {
            countRank[getRankValue(card)]++;
        }

        int maxRank = -1;
        for (int r = 12; r >= 0; r--) {
            if (countRank[r] >= 2) {
                maxRank = r;
                break;
            }
        }

        if (maxRank == -1) return null;

        List<String> candidates = new ArrayList<>();
        for (String card : cards) {
            if (getRankValue(card) == maxRank) {
                candidates.add(card);
            }
        }

        return new Results("pair", candidates.subList(0, 2).toArray(new String[0]));
    }

    private Results checkFirstGroup(String[] cards) {
        String maxCard = null;
        int maxRank = -1;

        for (String card : cards) {
            int currentRank = getRankValue(card);
            if (currentRank > maxRank) {
                maxRank = currentRank;
                maxCard = card;
            } else if (currentRank == maxRank) {
                // If multiple cards have the same rank, any can be chosen
                if (maxCard == null) {
                    maxCard = card;
                }
            }
        }

        return new Results("single card", new String[]{maxCard});
    }

    private int getRankValue(String card) {
        String rankPart = card.substring(0, card.length() - 1);
        switch (rankPart) {
            case "2": return 0;
            case "3": return 1;
            case "4": return 2;
            case "5": return 3;
            case "6": return 4;
            case "7": return 5;
            case "8": return 6;
            case "9": return 7;
            case "10": return 8;
            case "J": return 9;
            case "Q": return 10;
            case "K": return 11;
            case "A": return 12;
            default: throw new IllegalArgumentException("Invalid card: " + card);
        }
    }
}
