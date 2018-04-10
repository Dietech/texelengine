package ch.texelengine.testing;

import java.util.Random;

public interface TestRandomizer {

    //Fix a seed to ensure reproductibility
    public final static int SEED = 2018;
    
    public final static int RANDOM_ITERATIONS = 50;
    
    public static Random newRandom() {
        return new Random(SEED);
    }
}
