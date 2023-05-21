package com.memorytest.memorytest;

import java.util.*;

public class GameAlgorithm {
    private final Random random = new Random();
    private List<Integer> indexLocations;

    public List<Integer> generateGameTiles(int length) {
        indexLocations = this.generateIndexLocations(length);

        List<Integer> tiles = Arrays.asList(new Integer[25]);
        Collections.fill(tiles, 0);

        int i = 1;
        for (int location : indexLocations) {
            tiles.set(location, i++);
        }
        return tiles;
    }

    private List<Integer> generateIndexLocations(int length) {
        Set<Integer> locations = new HashSet<>();
        while (locations.size() != length) {
            int randomLocation = random.nextInt(0, 25);
            locations.add(randomLocation);
        }

        // Converting set to list to shuffle the generated indexes
        List<Integer> temp = new ArrayList<>(locations);
        Collections.shuffle(temp);
        return temp;
    }

    public List<Integer> getIndexLocations() {
        return indexLocations;
    }
}
