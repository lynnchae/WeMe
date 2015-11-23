package com.daoke.mobileserver.report.dto;

/**
 * 停车场实体类
 * 
 * @author wangliming
 * @date 2014-9-10 下午4:17:44
 * @version 1.0
 */
public class Parking {

	private Long business_id; // 商户ID
	private String name; // 商户名
	private String address; // 地址
	private String telephone; // 带区号的电话
	private String city; // 所在城市
	private float latitude; // 纬度坐标
	private float longitude; // 经度坐标
	private float avg_rating; // 星级评分，5.0代表五星，4.5代表四星半，依此类推
	private String rating_img_url; // 星级图片链接
	private String rating_s_img_url; // 小尺寸星级图片链接
	private int product_grade; // 产品/食品口味评价，1:一般，2:尚可，3:好，4:很好，5:非常好
	private int decoration_grade; // 环境评价，1:一般，2:尚可，3:好，4:很好，5:非常好
	private int service_grade; // 服务评价，1:一般，2:尚可，3:好，4:很好，5:非常好
	private float product_score; // 产品/食品口味评价单项分，精确到小数点后一位（十分制）
	private float decoration_score; // 环境评价单项分，精确到小数点后一位（十分制）
	private float service_score; // 服务评价单项分，精确到小数点后一位（十分制）
	private int avg_price; // 人均价格，单位:元，若没有人均，返回-1
	private int review_count; // 点评数量
	private String review_list_url; // 点评页面URL链接
	private int distance; // 商户与参数坐标的距离，单位为米，如不传入经纬度坐标，结果为-1
	private String business_url; // 商户页面链接
	private String photo_url; // 照片链接，照片最大尺寸700×700
	private String s_photo_url; // 小尺寸照片链接，照片最大尺寸278×200
	private int photo_count; // 照片数量
	private String photo_list_url; // 照片页面URL链接
	private int has_coupon; // 是否有优惠券，0:没有，1:有
	private int coupon_id; // 优惠券ID
	private String coupon_description; // 优惠券描述
	private String coupon_url; // 优惠券页面链接
	private int has_online_reservation; // 是否有在线预订，0:没有，1:有
	private String online_reservation_url; // 在线预订页面链接，目前仅返回HTML5站点链接

	/**
	 * 获取 business_id
	 * 
	 * @return business_id
	 */
	public Long getBusiness_id() {
		return business_id;
	}

	/**
	 * 设置 business_id
	 * 
	 * @param business_id
	 */
	public void setBusiness_id(Long business_id) {
		this.business_id = business_id;
	}

	/**
	 * 获取 name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置 name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取 address
	 * 
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 设置 address
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 获取 telephone
	 * 
	 * @return telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * 设置 telephone
	 * 
	 * @param telephone
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * 获取 city
	 * 
	 * @return city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * 设置 city
	 * 
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 获取 latitude
	 * 
	 * @return latitude
	 */
	public float getLatitude() {
		return latitude;
	}

	/**
	 * 设置 latitude
	 * 
	 * @param latitude
	 */
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	/**
	 * 获取 longitude
	 * 
	 * @return longitude
	 */
	public float getLongitude() {
		return longitude;
	}

	/**
	 * 设置 longitude
	 * 
	 * @param longitude
	 */
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	/**
	 * 获取 avg_rating
	 * 
	 * @return avg_rating
	 */
	public float getAvg_rating() {
		return avg_rating;
	}

	/**
	 * 设置 avg_rating
	 * 
	 * @param avg_rating
	 */
	public void setAvg_rating(float avg_rating) {
		this.avg_rating = avg_rating;
	}

	/**
	 * 获取 rating_img_url
	 * 
	 * @return rating_img_url
	 */
	public String getRating_img_url() {
		return rating_img_url;
	}

	/**
	 * 设置 rating_img_url
	 * 
	 * @param rating_img_url
	 */
	public void setRating_img_url(String rating_img_url) {
		this.rating_img_url = rating_img_url;
	}

	/**
	 * 获取 rating_s_img_url
	 * 
	 * @return rating_s_img_url
	 */
	public String getRating_s_img_url() {
		return rating_s_img_url;
	}

	/**
	 * 设置 rating_s_img_url
	 * 
	 * @param rating_s_img_url
	 */
	public void setRating_s_img_url(String rating_s_img_url) {
		this.rating_s_img_url = rating_s_img_url;
	}

	/**
	 * 获取 product_grade
	 * 
	 * @return product_grade
	 */
	public int getProduct_grade() {
		return product_grade;
	}

	/**
	 * 设置 product_grade
	 * 
	 * @param product_grade
	 */
	public void setProduct_grade(int product_grade) {
		this.product_grade = product_grade;
	}

	/**
	 * 获取 decoration_grade
	 * 
	 * @return decoration_grade
	 */
	public int getDecoration_grade() {
		return decoration_grade;
	}

	/**
	 * 设置 decoration_grade
	 * 
	 * @param decoration_grade
	 */
	public void setDecoration_grade(int decoration_grade) {
		this.decoration_grade = decoration_grade;
	}

	/**
	 * 获取 service_grade
	 * 
	 * @return service_grade
	 */
	public int getService_grade() {
		return service_grade;
	}

	/**
	 * 设置 service_grade
	 * 
	 * @param service_grade
	 */
	public void setService_grade(int service_grade) {
		this.service_grade = service_grade;
	}

	/**
	 * 获取 product_score
	 * 
	 * @return product_score
	 */
	public float getProduct_score() {
		return product_score;
	}

	/**
	 * 设置 product_score
	 * 
	 * @param product_score
	 */
	public void setProduct_score(float product_score) {
		this.product_score = product_score;
	}

	/**
	 * 获取 decoration_score
	 * 
	 * @return decoration_score
	 */
	public float getDecoration_score() {
		return decoration_score;
	}

	/**
	 * 设置 decoration_score
	 * 
	 * @param decoration_score
	 */
	public void setDecoration_score(float decoration_score) {
		this.decoration_score = decoration_score;
	}

	/**
	 * 获取 service_score
	 * 
	 * @return service_score
	 */
	public float getService_score() {
		return service_score;
	}

	/**
	 * 设置 service_score
	 * 
	 * @param service_score
	 */
	public void setService_score(float service_score) {
		this.service_score = service_score;
	}

	/**
	 * 获取 avg_price
	 * 
	 * @return avg_price
	 */
	public int getAvg_price() {
		return avg_price;
	}

	/**
	 * 设置 avg_price
	 * 
	 * @param avg_price
	 */
	public void setAvg_price(int avg_price) {
		this.avg_price = avg_price;
	}

	/**
	 * 获取 review_count
	 * 
	 * @return review_count
	 */
	public int getReview_count() {
		return review_count;
	}

	/**
	 * 设置 review_count
	 * 
	 * @param review_count
	 */
	public void setReview_count(int review_count) {
		this.review_count = review_count;
	}

	/**
	 * 获取 review_list_url
	 * 
	 * @return review_list_url
	 */
	public String getReview_list_url() {
		return review_list_url;
	}

	/**
	 * 设置 review_list_url
	 * 
	 * @param review_list_url
	 */
	public void setReview_list_url(String review_list_url) {
		this.review_list_url = review_list_url;
	}

	/**
	 * 获取 distance
	 * 
	 * @return distance
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * 设置 distance
	 * 
	 * @param distance
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}

	/**
	 * 获取 business_url
	 * 
	 * @return business_url
	 */
	public String getBusiness_url() {
		return business_url;
	}

	/**
	 * 设置 business_url
	 * 
	 * @param business_url
	 */
	public void setBusiness_url(String business_url) {
		this.business_url = business_url;
	}

	/**
	 * 获取 photo_url
	 * 
	 * @return photo_url
	 */
	public String getPhoto_url() {
		return photo_url;
	}

	/**
	 * 设置 photo_url
	 * 
	 * @param photo_url
	 */
	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}

	/**
	 * 获取 s_photo_url
	 * 
	 * @return s_photo_url
	 */
	public String getS_photo_url() {
		return s_photo_url;
	}

	/**
	 * 设置 s_photo_url
	 * 
	 * @param s_photo_url
	 */
	public void setS_photo_url(String s_photo_url) {
		this.s_photo_url = s_photo_url;
	}

	/**
	 * 获取 photo_count
	 * 
	 * @return photo_count
	 */
	public int getPhoto_count() {
		return photo_count;
	}

	/**
	 * 设置 photo_count
	 * 
	 * @param photo_count
	 */
	public void setPhoto_count(int photo_count) {
		this.photo_count = photo_count;
	}

	/**
	 * 获取 photo_list_url
	 * 
	 * @return photo_list_url
	 */
	public String getPhoto_list_url() {
		return photo_list_url;
	}

	/**
	 * 设置 photo_list_url
	 * 
	 * @param photo_list_url
	 */
	public void setPhoto_list_url(String photo_list_url) {
		this.photo_list_url = photo_list_url;
	}

	/**
	 * 获取 has_coupon
	 * 
	 * @return has_coupon
	 */
	public int getHas_coupon() {
		return has_coupon;
	}

	/**
	 * 设置 has_coupon
	 * 
	 * @param has_coupon
	 */
	public void setHas_coupon(int has_coupon) {
		this.has_coupon = has_coupon;
	}

	/**
	 * 获取 coupon_id
	 * 
	 * @return coupon_id
	 */
	public int getCoupon_id() {
		return coupon_id;
	}

	/**
	 * 设置 coupon_id
	 * 
	 * @param coupon_id
	 */
	public void setCoupon_id(int coupon_id) {
		this.coupon_id = coupon_id;
	}

	/**
	 * 获取 coupon_description
	 * 
	 * @return coupon_description
	 */
	public String getCoupon_description() {
		return coupon_description;
	}

	/**
	 * 设置 coupon_description
	 * 
	 * @param coupon_description
	 */
	public void setCoupon_description(String coupon_description) {
		this.coupon_description = coupon_description;
	}

	/**
	 * 获取 coupon_url
	 * 
	 * @return coupon_url
	 */
	public String getCoupon_url() {
		return coupon_url;
	}

	/**
	 * 设置 coupon_url
	 * 
	 * @param coupon_url
	 */
	public void setCoupon_url(String coupon_url) {
		this.coupon_url = coupon_url;
	}

	/**
	 * 获取 has_online_reservation
	 * 
	 * @return has_online_reservation
	 */
	public int getHas_online_reservation() {
		return has_online_reservation;
	}

	/**
	 * 设置 has_online_reservation
	 * 
	 * @param has_online_reservation
	 */
	public void setHas_online_reservation(int has_online_reservation) {
		this.has_online_reservation = has_online_reservation;
	}

	/**
	 * 获取 online_reservation_url
	 * 
	 * @return online_reservation_url
	 */
	public String getOnline_reservation_url() {
		return online_reservation_url;
	}

	/**
	 * 设置 online_reservation_url
	 * 
	 * @param online_reservation_url
	 */
	public void setOnline_reservation_url(String online_reservation_url) {
		this.online_reservation_url = online_reservation_url;
	}

}
