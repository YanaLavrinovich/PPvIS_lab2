package Model;

public class SocialWork {
    private String typeWork;
    private int numberWork;
    private int sem;

    public SocialWork() {
        typeWork = "-";
        numberWork = 0;
        sem = 0;
    }

    public SocialWork(String nameWork, int numberWork, int sem) {
        this.typeWork = nameWork;
        this.numberWork = numberWork;
        this.sem = sem;
    }

    public String getTypeWork() {
        return typeWork;
    }

    public void setTypeWork(String typeWork) {
        this.typeWork = typeWork;
    }

    public int getNumberWork() {
        return numberWork;
    }

    public void setNumberWork(int numberWork) {
        this.numberWork = numberWork;
    }

    public int getSem() {
        return sem;
    }

    public void setSem(int sem) {
        this.sem = sem;
    }

    @Override
    public String toString() {
        if (typeWork.equals("-")) {
            return typeWork;
        } else {
            return typeWork + " - " +
                    numberWork;
        }
    }
}
