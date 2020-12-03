package database;

import java.util.ArrayList;

import static database.Profile.*;

public class Database {
    private static ArrayList<Profile> profiles;
    private static ArrayList<File> files;

    public static void initDatabase(){
        profiles = dummyProfiles();
        files = dummyFiles();
    }

    public static int signIntoProfile(){
        //dummy algorithm, returns first profile with index 0
        return 0;
    }

    public static String getProfileName(int index){
        return profiles.get(index).getName();
    }

    public static boolean[] getProfileClassifiers(int index){
        boolean[] classifiers = new boolean[NUM_CLASSIFIERS];
        Profile profile = profiles.get(index);
        boolean[] profileClassifiers = profile.getClassifiers();

        System.arraycopy(profileClassifiers, 0, classifiers, 0, profileClassifiers.length);

        return classifiers;
    }

    public static boolean isProfileViewable(int index){
        return profiles.get(index).isViewable();
    }

    public static void setProfileClassifiers(int index, boolean[] classifiers){
        assert (classifiers.length == NUM_CLASSIFIERS);

        Profile profile = profiles.get(index);
        for (int i = 0; i < classifiers.length; i++){
            profile.setClassifier(i, classifiers[i]);
        }
    }

    public static ArrayList<String> searchForProfiles(int profileIndex){
        //dummy algorithm, returns profile data of other profiles that are viewable
        assert (profiles.get(profileIndex).isViewable());

        ArrayList<String> profileData = new ArrayList<>();

        Profile profile;
        for (int i = 0; i < profiles.size(); i++){
            if (i == profileIndex)
                continue;

            profile = profiles.get(i);

            if (profile.isViewable())
                profileData.add(profile.getName() + " | " + profile.getEmail());
        }

        return profileData;
    }

    public static ArrayList<String> getProfiles(){
        ArrayList<String> profileData = new ArrayList<>();

        Profile profile;
        for (Profile value : profiles){
            profile = value;
            profileData.add(profile.getName() + " | " + profile.getEmail());
        }

        return profileData;
    }

    public static String[] getFileNames(){
        String[] names = new String[files.size()];

        int i = 0;
        for (File file: files){
            names[i] = file.getName();
            i++;
        }

        return names;
    }

    public static String getFileData(int index){
        return files.get(index).getData();
    }

    public static void setFileDatum(ArrayList<Integer> indices, ArrayList<String> datum){
        assert(indices.size() == datum.size());

        for (int i = 0; i < indices.size(); i++){
            files.get(indices.get(i)).setData(datum.get(i));
        }
    }

    private static ArrayList<Profile> dummyProfiles(){
        Profile profile1 = new Profile("Profile Name 1", "profile1@hawk.iit.edu");
        profile1.setClassifier(ON_CAMPUS, true);
        profile1.setClassifier(CLEAN, true);
        profile1.setClassifier(MUSIC, true);
        profile1.setClassifier(GAMES, true);

        Profile profile2 = new Profile("Profile Name 2", "profile2@hawk.iit.edu");
        profile2.setClassifier(VIEWABLE, true);
        profile2.setClassifier(OFF_CAMPUS, true);
        profile2.setClassifier(SPORTS, true);

        Profile profile3 = new Profile("Profile Name 3", "profile3@hawk.iit.edu");
        profile3.setClassifier(VIEWABLE, true);
        profile3.setClassifier(ON_CAMPUS, true);
        profile3.setClassifier(OFF_CAMPUS, true);
        profile3.setClassifier(CLEAN, true);
        profile3.setClassifier(GAMES, true);

        ArrayList<Profile> dummyProfiles =  new ArrayList<>();
        dummyProfiles.add(profile1);
        dummyProfiles.add(profile2);
        dummyProfiles.add(profile3);
        return dummyProfiles;
    }

    private static ArrayList<File> dummyFiles(){
        File file1 = new File("file1.txt");
        file1.setData("*Not Editable In Prototype* data1");

        File file2 = new File("file2.txt");
        file2.setData("*Not Editable In Prototype* data2");

        File file3 = new File("file3.txt");
        file3.setData("*Not Editable In Prototype* data3");

        ArrayList<File> dummyFiles = new ArrayList<>();
        dummyFiles.add(file1);
        dummyFiles.add(file2);
        dummyFiles.add(file3);
        return dummyFiles;
    }
}
