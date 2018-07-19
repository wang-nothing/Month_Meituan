package com.example.admin.month_meituan.bean;

public class AddressBean {
    /**
     * status : 200
     * message : 获取位置信息成功
     * data : {"address":"北京市海淀区长春桥路17号","location":{"lat":39.95933,"lng":116.29845,"city":"北京"}}
     */

    private String status;
    private String message;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * address : 北京市海淀区长春桥路17号
         * location : {"lat":39.95933,"lng":116.29845,"city":"北京"}
         */

        private String address;
        private LocationBean location;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public static class LocationBean {
            /**
             * lat : 39.95933
             * lng : 116.29845
             * city : 北京
             */

            private double lat;
            private double lng;
            private String city;

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }
        }
    }
}
