package unsw.loopmania.Cards;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;

import unsw.loopmania.LoopManiaWorld;

public interface cardAllowedTiles {
    List<Pair<Integer, Integer>> getvalidTiles(LoopManiaWorld world);
}


class onlyPathTiles implements cardAllowedTiles{

    @Override
    public List<Pair<Integer, Integer>> getvalidTiles(LoopManiaWorld world) {
        return world.getPath();
    }

}


class onlyadjacentPathTiles implements cardAllowedTiles{

    @Override
    public List<Pair<Integer, Integer>> getvalidTiles(LoopManiaWorld world) {

        List<Pair<Integer, Integer>> tempPath = world.getPath();

        List<Pair<Integer, Integer>> validTiles = new ArrayList<Pair<Integer, Integer>>();
        int max_width = world.getWidth();
        int max_height = world.getHeight();
        for (Pair<Integer, Integer> tile : tempPath) {
            int x_coord = (int) tile.getValue0();
            int y_coord = (int) tile.getValue1();
            
            if((x_coord+1) < max_width){
                Pair<Integer, Integer> temp = new Pair<>(x_coord+1, y_coord);

                if(!tempPath.contains(temp)){
                    validTiles.add(temp);
                }

            }
            if ((x_coord - 1) >= 0) {
                Pair<Integer, Integer> temp = new Pair<>(x_coord - 1, y_coord);

                if (!tempPath.contains(temp)) {
                    validTiles.add(temp);
                }

            }
            if ((y_coord + 1) < max_height) {
                Pair<Integer, Integer> temp = new Pair<>(x_coord - 1, y_coord);

                if (!tempPath.contains(temp)) {
                    validTiles.add(temp);
                }

            }

            if ((y_coord - 1) >= 0) {
                Pair<Integer, Integer> temp = new Pair<>(x_coord - 1, y_coord);

                if (!tempPath.contains(temp)) {
                    validTiles.add(temp);
                }

            }

            if ((x_coord + 1 < max_width) && (y_coord + 1 < max_height)) {
                Pair<Integer, Integer> temp = new Pair<>(x_coord + 1, y_coord + 1);

                if (!tempPath.contains(temp)) {
                    validTiles.add(temp);
                }

            }

            if ((x_coord - 1 >= 0) && (y_coord + 1 < max_height)) {
                Pair<Integer, Integer> temp = new Pair<>(x_coord - 1, y_coord + 1);

                if (!tempPath.contains(temp)) {
                    validTiles.add(temp);
                }
            }

            if ((x_coord + 1 < max_width) && (y_coord - 1 >= 0)) {
                Pair<Integer, Integer> temp = new Pair<>(x_coord + 1, y_coord - 1);

                if (!tempPath.contains(temp)) {
                    validTiles.add(temp);
                }
            }

            if ((x_coord - 1 >= 0) && (y_coord - 1 >= 0)) {
                Pair<Integer, Integer> temp = new Pair<>(x_coord - 1, y_coord - 1);

                if (!tempPath.contains(temp)) {
                    validTiles.add(temp);
                }
            }

        }


        return validTiles;
    }

}
