package gameUtils;

import java.util.Random;

public class RandomGenerator {
	 Random r;
     int range;
     public RandomGenerator(int range) {
  	   this.range = range;
  	   r= new Random(range);
     }
     public int getRandomGenerator() {
  	return r.nextInt(range);
     }

}
