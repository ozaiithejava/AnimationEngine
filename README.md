# AnimationEngine
i translated in turkhis orginal code :https://github.com/nullpointerexceptionkek/AnimationEngine/blob/main/AnimationEngine.java

## Usage:
```Java
public class AnimationExample {
    public static void main(String[] args) {
        // Örnek 1: Temel kullanım
        AnimationEngine animation1 = new AnimationEngine(0, 100, 1000);
        while (!animation1.isAnimationDone()) {
            float currentValue = animation1.getAnimationValue();
            System.out.println("Current Value: " + currentValue);
        }

        // Örnek 2: Hızlı animasyonu etkinleştirme
        AnimationEngine animation2 = new AnimationEngine(50, 200, 500, true);
        while (!animation2.isAnimationDone()) {
            float currentValue = animation2.getAnimationValue();
            System.out.println("Current Value: " + currentValue);
        }

        // Örnek 3: Geriye doğru animasyon
        AnimationEngine animation3 = new AnimationEngine(100, 0, 800);
        while (!animation3.isAnimationDone()) {
            float currentValue = animation3.getAnimationValue();
            System.out.println("Current Value: " + currentValue);
        }

        // Örnek 4: Parametre güncelleme
        AnimationEngine animation4 = new AnimationEngine();
        animation4.AnimationUpdateValue(30, 90, 600);
        while (!animation4.isAnimationDone()) {
            float currentValue = animation4.getAnimationValue();
            System.out.println("Current Value: " + currentValue);
        }
    }
}
```
