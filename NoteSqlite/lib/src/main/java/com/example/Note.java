package com.example;

public class Note implements Comparable<Note>{
    private Integer id;
    private String note;

    public void setId(int i){
        id =i;
    }

    public Integer getId(){
        return id;
    }

    public void setNote(String s){
        note = s;
    }
    public String getNote(){
        return note;
    }

    @Override
    public int compareTo(Note o) {
        return this.id.compareTo(o.id);
    }


}
