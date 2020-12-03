package database;

public class File {
    private String name;
    private String data;

    public File(String name){
        this.name = name;
        this.data = "";
    }

    public String getName(){
        return name;
    }

    public String getData(){
        return data;
    }

    public void setData(String data){
        this.data = data;
    }
}
