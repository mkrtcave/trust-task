package trask.junior.task.model;

public enum Level {
    One(1), Two(2), Three(3), Four(4), Five(5), Six(6), Seven(7), Eight(8), Nine(9),
    Ten(10) ;
    private int num;
    Level(int num){
        this.num = num;
    }

    public static Level getLevel(int num){
        return Level.values()[num-1];
    }

    public int getNum() {
        return num;
    }
}
