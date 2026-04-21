import static org.junit.Assert.*;

import java.util.List;

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
    public void testPossibleMoves_allDirectionBlockedByTwo() {
        int[][] island = {
                { 2, 2, 2 },
                { 2, 3, 2 },
                { 2, 2, 2 }

        };
        int[] location = { 1, 1 };
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        assertTrue(moves.isEmpty());
    }

    // Add more tests here!
    // Come up with varied cases
}
