package com.ceva.core1.ch09.treeSet;

public record Item(String description, int partNumber) implements Comparable<Item> {
    @Override
    public int compareTo(Item other) {
        int diff = Integer.compare(partNumber, other.partNumber);
        return diff != 0 ? diff : description.compareTo(other.description);
    }
}
