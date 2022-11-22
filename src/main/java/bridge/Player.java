package bridge;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static bridge.Result.*;

public class Player {
    private final List<String> bridges;
    private final List<String> directions = new ArrayList<>();
    private int playCount;

    public Player(List<String> bridge) {
        this.bridges = bridge;
        this.playCount = 1;
    }

    public List<String> getBridges() {
        return bridges;
    }

    public List<String> getDirections() {
        return directions;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void addDirection(String direction) {
        directions.add(direction);
    }

    public boolean isGameEnd() {
        return directions.size() == bridges.size();
    }

    public void resetDirections() {
        directions.clear();
        playCount++;
    }

    public boolean isMovePossible() {
        int index = directions.size() - 1;
        return directions.get(index).equals(bridges.get(index));
    }

    public List<Result> createMapInformation(String target) {
        List<Result> results = new ArrayList<>();
        IntStream.range(0, directions.size())
                .forEach(index -> addResults(target, results, index));
        return results;
    }

    private void addResults(String target, List<Result> results, int index) {
        String direction = directions.get(index);
        String bridge = bridges.get(index);

        Result result = getResult(target, direction, bridge);
        results.add(result);

        addDiv(results, index);
    }

    private Result getResult(String target, String direction, String bridge) {
        if (isMovingPossible(target, direction, bridge)) {
            return POSSIBLE;
        }
        if (isMovingImpossible(target, direction, bridge)) {
            return IMPOSSIBLE;
        }
        return BLANK;
    }

    private void addDiv(List<Result> results, int index) {
        if (!isLastIndex(index)) {
            results.add(DIV);
        }
    }

    private boolean isLastIndex(int index) {
        return index == directions.size() - 1;
    }

    private boolean isMovingPossible(String target, String direction, String bridge) {
        return direction.equals(target) && direction.equals(bridge);
    }

    private boolean isMovingImpossible(String target, String direction, String bridge) {
        return direction.equals(target) && !direction.equals(bridge);
    }
}
