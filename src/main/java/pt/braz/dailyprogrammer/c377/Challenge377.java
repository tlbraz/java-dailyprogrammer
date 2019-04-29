package pt.braz.dailyprogrammer.c377;

import io.vavr.Function2;
import io.vavr.Function4;
import io.vavr.Function6;
import io.vavr.collection.List;
import pt.braz.dailyprogrammer.Challenge;

import static org.assertj.core.api.BDDAssertions.then;

public class Challenge377 extends Challenge {

    public Challenge377() {
        super("https://www.reddit.com/r/dailyprogrammer/comments/bazy5j/20190408_challenge_377_easy_axisaligned_crate/");
    }

    public static void main(String[] args) {
        new Challenge377().execute();
    }

    @Override
    public void tests() {
        then(fit.apply(25, 18, 6, 5)).isEqualTo(12);
        then(fit.apply(10, 10, 1, 1)).isEqualTo(100);
        then(fit.apply(12, 34, 5, 6)).isEqualTo(10);
        then(fit.apply(12345, 678910, 1112, 1314)).isEqualTo(5676);
        then(fit.apply(5, 100, 6, 1)).isEqualTo(0);

        then(fit2.apply(25, 18, 6, 5)).isEqualTo(15);
        then(fit2.apply(12, 34, 5, 6)).isEqualTo(12);
        then(fit2.apply(12345, 678910, 1112, 1314)).isEqualTo(5676);
        then(fit2.apply(5, 5, 3, 2)).isEqualTo(2);
        then(fit2.apply(5, 100, 6, 1)).isEqualTo(80);
        then(fit2.apply(5, 5, 6, 1)).isEqualTo(0);

        then(fit3.apply(10, 10, 10, 1, 1, 1)).isEqualTo(1000);
        then(fit3.apply(12, 34, 56, 7, 8, 9)).isEqualTo(32);
        then(fit3.apply(123, 456, 789, 10, 11, 12)).isEqualTo(32604);
        then(fit3.apply(1234567, 89101112, 13141516, 171819, 202122, 232425)).isEqualTo(174648);

        then(fitn.apply(List.of(3, 4), List.of(1, 2))).isEqualTo(6);
        then(fitn.apply(List.of(123, 456, 789), List.of(10, 11, 12))).isEqualTo(32604);
        then(fitn.apply(List.of(123, 456, 789, 1011, 1213, 1415), List.of(16, 17, 18, 19, 20, 21))).isEqualTo(1883443968);

    }

    private Function2<List<Integer>, List<Integer>, Integer> computeByAxis = (worldSize, boxSize) ->
            worldSize.zipWith(boxSize, Math::floorDiv).reduce(Math::multiplyExact);

    private Function4<Integer, Integer, Integer, Integer, Integer> fit = (xAxis, yAxis, xWidth, yWidth) ->
            computeByAxis.apply(
                    List.of(xAxis, yAxis),
                    List.of(xWidth, yWidth)
            );

    private Function4<Integer, Integer, Integer, Integer, Integer> fit2 = (xAxis, yAxis, xWidth, yWidth) -> {
        List<Integer> worldSize = List.of(xAxis, yAxis);
        List<Integer> boxSize = List.of(xWidth, yWidth);
        return boxSize.permutations().map(size -> computeByAxis.apply(worldSize, size)).reduce(Math::max);
    };


    private Function6<Integer, Integer, Integer, Integer, Integer, Integer, Integer> fit3 = (xAxis, yAxis, zAxis, xWidth, yWidth, zWidth) -> {
        List<Integer> worldSize = List.of(xAxis, yAxis, zAxis);
        List<Integer> boxSize = List.of(xWidth, yWidth, zWidth);
        return boxSize.permutations().map(size -> computeByAxis.apply(worldSize, size)).reduce(Math::max);
    };

    private Function2<List<Integer>, List<Integer>, Integer> fitn = (worldSize, boxSize) ->
        boxSize.permutations().map(size -> computeByAxis.apply(worldSize, size)).reduce(Math::max);
}
