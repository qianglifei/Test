package com.beikong.hdunemployedperson.CustomView;

public class PieItemBean{
        private String itemType;
        private float itemValue;

        public PieItemBean(float itemValue, String itemType) {
            this.itemValue = itemValue;
            this.itemType = itemType;
        }

        public String getItemType() {
            return itemType;
        }

        public void setItemType(String itemType) {
            this.itemType = itemType;
        }

        public float getItemValue() {
            return itemValue;
        }

        public void setItemValue(float itemValue) {
            this.itemValue = itemValue;
        }
    }