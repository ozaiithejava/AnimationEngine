/**
 * AnimationEngine sınıfı, bir değerin belirli bir sürede belirli bir değere animasyonla geçişini sağlar.
 * @param startValue Başlangıç değeri.
 * @param endValue Bitiş değeri.
 * @param time Animasyon süresi (milisaniye cinsinden).
 * @param instaIsDrawAnimation Hızlı animasyonu etkinleştiren bir bayrak.
 */
public class AnimationEngine {

    private long prevTime;
    private float animationValue;
    private float startValue;
    private float endValue;
    private boolean isIncreasing;
    private float changeValuePms;
    private boolean isDrawAnimation = false;
    private boolean resetUsingBackWardsAnimation = false;

    /**
     * Parametrelerle bir AnimationEngine örneği oluşturur.
     * @param startValue Başlangıç değeri.
     * @param endValue Bitiş değeri.
     * @param time Animasyon süresi (milisaniye cinsinden).
     */
    public AnimationEngine(float startValue, float endValue, long time) {
        this.prevTime = System.currentTimeMillis();
        this.startValue = startValue;
        this.endValue = (startValue == endValue ? endValue + 1 : endValue);
        this.animationValue = startValue;
        this.isIncreasing = endValue > startValue;
        float animationDistance = Math.abs(startValue - endValue);
        this.changeValuePms = animationDistance / time;
    }

    /**
     * Parametrelerle bir AnimationEngine örneği oluşturur ve anında animasyonu etkinleştirir.
     * @param startValue Başlangıç değeri.
     * @param endValue Bitiş değeri.
     * @param time Animasyon süresi (milisaniye cinsinden).
     * @param instaIsDrawAnimation Hızlı animasyonu etkinleştiren bir bayrak.
     */
    public AnimationEngine(float startValue, float endValue, long time, boolean instaIsDrawAnimation) {
        this.prevTime = System.currentTimeMillis();
        this.startValue = startValue;
        this.endValue = (startValue == endValue ? endValue + 1 : endValue);
        this.animationValue = startValue;
        this.isIncreasing = endValue > startValue;
        float animationDistance = Math.abs(startValue - endValue);
        this.changeValuePms = animationDistance / time;
        this.isDrawAnimation = instaIsDrawAnimation;
    }

    /**
     * Boş bir AnimationEngine örneği oluşturur.
     */
    public AnimationEngine() {
    }

    /**
     * Animasyon değerini alır ve günceller.
     * @return Animasyon değeri.
     */
    public float getAnimationValue() {
        updateAnimationValue();
        return animationValue;
    }

    /**
     * Animasyonun tamamlandığını kontrol eder.
     * @return Animasyon tamamlandıysa true, aksi takdirde false.
     */
    public boolean isAnimationDone() {
        return animationValue == endValue;
    }

    /**
     * Animasyon değerini günceller.
     */
    private void updateAnimationValue() {
        if (isDrawAnimation) {
            resetUsingBackWardsAnimation = false;
            if (animationValue == endValue) return;

            if (isIncreasing) {
                if (animationValue >= endValue) {
                    animationValue = endValue;
                    return;
                }

                animationValue += (changeValuePms) * (System.currentTimeMillis() - prevTime);

                if (animationValue > endValue)
                    animationValue = endValue;
                this.prevTime = System.currentTimeMillis();
                return;
            } else {
                if (animationValue <= endValue) {
                    animationValue = endValue;
                    return;
                }
                animationValue -= (changeValuePms) * (System.currentTimeMillis() - prevTime);

                if (animationValue < endValue)
                    animationValue = endValue;
                this.prevTime = System.currentTimeMillis();
                return;
            }
        } else if (resetUsingBackWardsAnimation) {
            setIsDrawAnimation(false);
            if (animationValue == startValue) {
                reset();
                resetUsingBackWardsAnimation = false;
                return;
            }
            if (isIncreasing) {
                if (animationValue <= startValue) {
                    reset();
                    return;
                }
            }
            animationValue -= (changeValuePms) * (System.currentTimeMillis() - prevTime);
            if (animationValue < startValue)
                reset();
            this.prevTime = System.currentTimeMillis();
        }
    }

    /**
     * Animasyonu sıfırlar.
     */
    public void reset() {
        animationValue = startValue;
        prevTime = System.currentTimeMillis();
    }

    /**
     * Animasyonun parametrelerini günceller.
     * @param startValue Yeni başlangıç değeri.
     * @param endValue Yeni bitiş değeri.
     * @param time Yeni animasyon süresi (milisaniye cinsinden).
     */
    public void AnimationUpdateValue(float startValue, float endValue, long time) {
        reset();
        this.prevTime = System.currentTimeMillis();
        this.startValue = startValue;
        this.endValue = (startValue == endValue ? endValue + 1 : endValue);
        this.animationValue = startValue;
        this.isIncreasing = endValue > startValue;
        float animationDistance = Math.abs(startValue - endValue);
        this.changeValuePms = animationDistance / time;
    }

    /**
     * Animasyonun parametrelerini günceller ve anında animasyonu etkinleştirir.
     * @param startValue Yeni başlangıç değeri.
     * @param endValue Yeni bitiş değeri.
     * @param time Yeni animasyon süresi (milisaniye cinsinden).
     * @param instaDrawAnimation Hızlı animasyonu etkinleştiren bir bayrak.
     */
    public void AnimationUpdateValue(float startValue, float endValue, long time, boolean instaDrawAnimation) {
        this.prevTime = System.currentTimeMillis();
        this.startValue = startValue;
        this.endValue = (startValue == endValue ? endValue + 1 : endValue);
        this.animationValue = startValue;
        this.isIncreasing = endValue > startValue;
        float animationDistance = Math.abs(startValue - endValue);
        this.changeValuePms = animationDistance / time;
        this.isDrawAnimation = instaDrawAnimation;
    }

    /**
     * Animasyon çizimini etkinleştirir veya devre dışı bırakır.
     * @param drawAnimation Animasyon çizimini etkinleştirmek için true, devre dışı bırakmak için false.
     */
    public void setIsDrawAnimation(boolean drawAnimation) {
        this.isDrawAnimation = drawAnimation;
    }

    /**
     * Animasyon çiziminin durumunu alır.
     * @return Animasyon çizimi etkinse true, aksi takdirde false.
     */
    public boolean getIsDrawAnimation() {
        return isDrawAnimation;
    }

    /**
     * Geriye doğru animasyonu başlatır.
     */
    public void resetUsingBackWardsAnimation() {
        prevTime = System.currentTimeMillis();
        setIsDrawAnimation(false);
        this.resetUsingBackWardsAnimation = true;
    }
}
