package org.docksidestage.bizfw.basic.objanimal;

public class BarkingProcess {

    public BarkingProcess(Animal animal) {
        this.animal = animal;
    }

    private Animal animal;

    public BarkedSound bark() {
        animal.breatheIn();
        animal.prepareAbdominalMuscle();
        String barkWord = animal.getBarkWord();
        BarkedSound barkedSound = doBark(barkWord);
        return barkedSound;
    }

    protected BarkedSound doBark(String barkWord) {
        animal.downHitPoint();
        return new BarkedSound(barkWord);
    }
}
