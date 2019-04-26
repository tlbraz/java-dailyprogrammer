package pt.braz.dailyprogrammer.c377;

import io.vavr.Function2;
import io.vavr.Function4;
import pt.braz.dailyprogrammer.Challenge;

import static org.assertj.core.api.BDDAssertions.then;

public class Challange377 extends Challenge {

    public Challange377() {
        super("https://www.reddit.com/r/dailyprogrammer/comments/bazy5j/20190408_challenge_377_easy_axisaligned_crate/");
    }

    public static void main(String[] args) {
        new Challange377().execute();
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

    }

    private Function2<Integer, Integer, Integer> howManyFitOnAxis = Math::floorDiv;

    private Function2<Integer, Integer, Integer> multiply = Math::multiplyExact;

    private Function2<Integer, Integer, Integer> max = Math::max;

    private Function4<Integer, Integer, Integer, Integer, Integer> fit =(xAxis, yAxis, xWidth, yWidth) ->
            multiply.apply(howManyFitOnAxis.apply(xAxis, xWidth))
                    .apply(howManyFitOnAxis.apply(yAxis, yWidth));

    private Function4<Integer, Integer, Integer, Integer, Integer> rotatedFit = (xAxis, yAxis, xWidth, yWidth) ->
            fit.apply(xAxis, yAxis, yWidth, xWidth);

    private Function4<Integer, Integer, Integer, Integer, Integer> fit2 = (xAxis, yAxis, xWidth, yWidth) ->
            max.apply(fit.apply(xAxis, yAxis, xWidth, yWidth))
                    .apply(rotatedFit.apply(xAxis, yAxis, xWidth, yWidth));
}
