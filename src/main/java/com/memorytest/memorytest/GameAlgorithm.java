package com.memorytest.memorytest;

import java.util.*;

public class GameAlgorithm {

    public List<Integer> generateGameTiles(int length) {
        List<Integer> generatedRandomLocations = this.generateRandomIndexForNumberLocation(length);

        List<Integer> tiles = Arrays.asList(new Integer[25]);
        Collections.fill(tiles, 0);

        int i = 1;
        for (int location : generatedRandomLocations) {
            tiles.set(location, i++);
        }
        return tiles;
    }

    private List<Integer> generateRandomIndexForNumberLocation(int length) {
        Set<Integer> locations = new HashSet<>();
        while (locations.size() != length) {
            int randomLocation = new Random().nextInt(0, 25);
            locations.add(randomLocation);
        }

        // Converting set to list to shuffle the generated indexes
        List<Integer> temp = new ArrayList<>(locations);
        Collections.shuffle(temp);
        return temp;
    }
}
