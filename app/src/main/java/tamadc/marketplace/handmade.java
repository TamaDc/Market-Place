package tamadc.marketplace;

/**
 * Created by Asus A455L on 19/12/2017.
 */

public class handmade {
    private  int id;
    private String name;
    private String price;
    private byte[] image;
    private String keterangan;
    private  String kualitas;


    public handmade( String name, String price,byte[] image, String keterangan, String kualitas, int id) {

        this.name = name;
        this.price = price;
        this.image = image;
        this.keterangan = keterangan;
        this.kualitas = kualitas;
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public void setID(int id){
        this.id=id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getPrice(){
        return price;
    }

    public void setPrice(String price){
        this.price=price;
    }

    public byte[] getImage(){
        return image;
    }

    public void setImage(byte[] Image){
        this.image=image;
    }

    public String getKeterangan(){return keterangan;}

    public void setKeterangan(String keterangan){this.keterangan=keterangan;}

    public String getKualitas(){return kualitas;}

    public void setKualitas(){this.kualitas=kualitas;}



}



