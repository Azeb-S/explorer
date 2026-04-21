import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class ExplorerSearchTest {
    @Test
    public void testReachableArea_someUnreachable() {
        int[][] island = {
                { 1, 1, 1, 3, 1, 1 }, //
                { 3, 2, 3, 1, 3, 1 }, //
                { 1, 1, 1, 1, 3, 3 }, //
                { 3, 1, 2, 1, 0, 1 }, //
                { 1, 1, 1, 2, 1, 1 }, //
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(14, actual);
    }

    @Test
    public void testReachableArea_NoPath() {
        int[][] island = {
                { 1, 1, 1, 3, 1, 1 },
                { 2, 2, 3, 2, 3, 1 },
                { 1, 1, 2, 1, 3, 2 },
                { 3, 2, 1, 1, 2, 1 },
                { 2, 1, 2, 1, 1, 1 },
        };

        assertThrows(IllegalArgumentException.class, () -> {
            ExplorerSearch.reachableArea(island);
        });
    }

    @Test
    public void testReachableArea_centerOdGride() {
        int[][] island = {
                { 1, 1, 1 },
                { 1, 0, 1 },
                { 1, 1, 1 }
        };

        int[] expected = { 1, 1 };
        assertArrayEquals(expected, ExplorerSearch.reachableAreaLocation(island));
    }

    @Test
    public void testReachableArea_topLeftCorner() {
        int[][] island = {
                { 0, 1, 1 },
                { 1, 1, 1 },
                { 1, 1, 1 }
        };

        int[] expected = { 0, 0 };
        assertArrayEquals(expected, ExplorerSearch.reachableAreaLocation(island));
    }

    @Test
    public void testPossibleMoves_allDirectionBlockedByTwo() {
        int[][] island = {
                { 2, 2, 2 },
                { 2, 1, 2 },
                { 2, 2, 2 }

        };
        int[] location = { 1, 1 };
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        assertEquals(0, moves.size());
    }

    @Test
    public void testPossibleMoves_partialEdge() {
        int[][] island = {
                { 1, 3, 3 }
        };
        int[] location = { 0, 2 };
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);
        assertEquals(0, moves.size());

    }

    @Test
    public void testPossibleMoves_blockedRightByTwo() {
        int[][] island = {
                { 1, 1, 1 },
                { 1, 3, 2 },
                { 1, 1, 1 }
        };
        int[] location = { 1, 1 };
        Set<String> movSet = toSet(ExplorerSearch.possibleMoves(island, location));
        assertTrue(movSet.contains("0,1")); // up open
        assertTrue(movSet.contains("2,1")); // down open
        assertTrue(movSet.contains("1,0")); // left open
        assertFalse(movSet.contains("1,2")); // right blocked

    }

    @Test
    public void testPossibleMoves_allDirectionsOpen() {
        int[][] island = {
                { 1, 1, 1 },
                { 1, 3, 1 },
                { 1, 1, 1 }
        };
        int[] location = { 1, 1 };
        Set<String> movSet = toSet(ExplorerSearch.possibleMoves(island, location));
        assertTrue(movSet.contains("0,1")); // up open
        assertTrue(movSet.contains("2,1")); // down open
        assertTrue(movSet.contains("1,0")); // left open
        assertTrue(movSet.contains("1,2")); // right blocked

    }

    @Test
    public void testPossibleMoves_oneOpen_two_oneEdge() {
        int[][] island = {
                { 2, 2, 2 },
                { 1, 3, 1 },
                { 2, 2, 2 }
        };
        int[] location = { 1, 1 };
        Set<String> movSet = toSet(ExplorerSearch.possibleMoves(island, location));
        assertTrue(movSet.contains("1,2"));
        assertTrue(movSet.contains("1,0"));

    }

    @Test
    public void testPossibleMoves_blockedbelowByTwo() {
        int[][] island = {
                { 1, 1, 1 },
                { 1, 3, 3 },
                { 1, 2, 1 }
        };
        int[] location = { 1, 1 };
        Set<String> movSet = toSet(ExplorerSearch.possibleMoves(island, location));
        assertTrue(movSet.contains("0,1")); // up open
        assertFalse(movSet.contains("2,1")); // down open
        assertTrue(movSet.contains("1,0")); // left open
        assertFalse(movSet.contains("1,2")); // right blocked

    }

    @Test
    public void testPossibleMoves_blockedAboveByTwo() {
        int[][] island = {
                { 1, 2, 1 },
                { 1, 3, 3 },
                { 1, 3, 1 }
        };
        int[] location = { 1, 1 };
        Set<String> movSet = toSet(ExplorerSearch.possibleMoves(island, location));
        assertFalse(movSet.contains("0,1")); // up open
        assertFalse(movSet.contains("2,1")); // down open
        assertTrue(movSet.contains("1,0")); // left open
        assertFalse(movSet.contains("1,2")); // right blocked

    }

    private Set<String> toSet(List<int[]> list) {
        Set<String> set = new HashSet<>();
        for (int[] arr : list) {
            set.add(arr[0] + "," + arr[1]);
        }
        return set;
    }

    // Add more tests here!
    // Come up with varied cases
}
