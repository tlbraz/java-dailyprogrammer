package pt.braz.dailyprogrammer.c372;

import io.vavr.Function1;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import pt.braz.dailyprogrammer.Challenge;

import java.util.function.Function;

import static org.assertj.core.api.BDDAssertions.then;

public class Challenge372 extends Challenge {

    public Challenge372() {
        super("https://www.reddit.com/r/dailyprogrammer/comments/afxxca/20190114_challenge_372_easy_perfectly_balanced/");
    }

    public static void main(String[] args) {
        new Challenge372().execute();
    }

    @Override
    public void tests() {
       then(balanced.apply("xxxyyy")).isEqualTo(true);
       then(balanced.apply("yyyxxx")).isEqualTo(true);
       then(balanced.apply("xxxyyyy")).isEqualTo(false);
       then(balanced.apply("yyxyxxyxxyyyyxxxyxyx")).isEqualTo(true);
       then(balanced.apply("xyxxxxyyyxyxxyxxyy")).isEqualTo(false);
       then(balanced.apply("")).isEqualTo(true);
       then(balanced.apply("x")).isEqualTo(false);

       then(balanced_bonus.apply("xxxyyyzzz")).isEqualTo(true);
       then(balanced_bonus.apply("abccbaabccba")).isEqualTo(true);
       then(balanced_bonus.apply("xxxyyyzzzz")).isEqualTo(false);
       then(balanced_bonus.apply("abcdefghijklmnopqrstuvwxyz")).isEqualTo(true);
       then(balanced_bonus.apply("pqq")).isEqualTo(false);
       then(balanced_bonus.apply("fdedfdeffeddefeeeefddf")).isEqualTo(false);
       then(balanced_bonus.apply("www")).isEqualTo(true);
       then(balanced_bonus.apply("x")).isEqualTo(true);
       then(balanced_bonus.apply("")).isEqualTo(true);
    }

    Function1<String, Boolean> balanced = s -> {
        Tuple2<List<Character>, List<Character>> tuple = List.ofAll(s.toCharArray()).partition(p -> p.equals('x'));
        return tuple._1.size() == tuple._2.size();
    };

    Function1<String, Boolean> balanced_bonus = s -> s.isEmpty() ||
            List.ofAll(s.toCharArray())
                    .groupBy(Function.identity())
                    .map(p -> p._2.size())
                    .groupBy(Function.identity())
                    .size() == 1;
}