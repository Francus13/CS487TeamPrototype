package database;

public class Profile {
    public static final int VIEWABLE = 0;
    public static final int ON_CAMPUS = 1;
    public static final int OFF_CAMPUS = 2;
    public static final int CLEAN = 3;
    public static final int SPORTS = 4;
    public static final int MUSIC = 5;
    public static final int GAMES = 6;
    public static final int NUM_CLASSIFIERS = 7;

    private String name;
    private String email;
    private boolean[] classifiers;

    public Profile(String name, String email){
        this.name = name;
        this.email = email;
        classifiers = new boolean[NUM_CLASSIFIERS]; //java sets to all zeroes
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public boolean[] getClassifiers(){
        return classifiers;
    }

    public boolean isViewable(){
        return classifiers[VIEWABLE];
    }

    public void setClassifier(int classifier, boolean status){
        classifiers[classifier] = status;
    }
}
