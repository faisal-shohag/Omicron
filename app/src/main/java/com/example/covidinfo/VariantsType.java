package com.example.covidinfo;

public class VariantsType {
    int ImgId;
    String VariantsName;
    String VariantDetails;
    String SubText;

    public VariantsType(int imgId, String variantsName, String subText, String variantDetails) {
        ImgId = imgId;
        VariantsName = variantsName;
        VariantDetails = variantDetails;
        SubText = subText;
    }

    public String getSubText() {
        return SubText;
    }

    public void setSubText(String subText) {
        SubText = subText;
    }

    public String getVariantDetails() {
        return VariantDetails;
    }

    public void setVariantDetails(String variantDetails) {
        VariantDetails = variantDetails;
    }

    public int getImgId() {
        return ImgId;
    }

    public void setImgId(int imgId) {
        ImgId = imgId;
    }

    public String getVariantsName() {
        return VariantsName;
    }

    public void setVariantsName(String variantsName) {
        VariantsName = variantsName;
    }
}
