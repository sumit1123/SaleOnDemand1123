package com.example.grocery.Labels;

/**
 * Created by Mohd Afzal on 7/24/2018.
 */

public class ProductLabel {
    /**
     * product_label_id : 1
     * language_id : 1
     * latest_products : New Arrivals
     * recently_viewed_products : Recently Viewed
     * sort : Sort
     * sort_by : SORT BY
     * newest_first : Newest First
     * ascending_order : Assending Order
     * low_to_high : Low to High
     * high_to_low : High to Low
     * filter : Filter
     * min : Min
     * max : Max
     * search_by_brand_name : Search by brand name
     * select_brands : Select Brands
     * none_selected : None Selected
     * selected : Selected
     * apply : Apply
     * add : Add
     * off : off
     * add_more : Add more
     * enter_quantity : Enter Quantity
     * quantity : Quantity
     * cancel : CANCEL
     * save : SAVE
     * reviews_and_ratings : reviews and ratings
     * similar : Similar
     * highlights : Highlights
     * ratings_and_reviews : Ratings and Reviews
     * your_rating : Your Rating
     * rate_and_write_a_review : RATE AND WRITE A REVIEW
     * enter_your_review : Enter your review
     * add_review : ADD REVIEW
     * view_all_reviews : VIEW ALL REVIEWS
     * all_reviews : All Reviews
     * out_of_stock : OUT OF STOCK
     * search : Search
     * products : products
     * similar_products_of : Similar Products Of
     * available_variations_for : Available Variations for
     * empty_category_product_title : No products available
     * empty_category_product_description : Please check for another category
     * no_more_products_available : No more products available
     * max_viewed_products : Max Viewed Products
     * shop_by_category : Shop By Category
     * all_categories : All Categories
     * buy_now : Buy now
     * search_empty_title : Sorry, no result found
     * search_empty_description : Please check with different search keyword
     * max_viewed : Max Viewed
     * featured_brands : Featured Brands
     * write_a_review : WRITE A REVIEW
     * created_at : 2018-07-23 18:37:51
     * updated_at : 2018-07-23 18:37:51
     */

 /*   private int product_label_id;
    private int language_id;
    private String latest_products;
    private String recently_viewed_products;
    private String sort;
    private String sort_by;
    private String newest_first;
    private String ascending_order;
    private String low_to_high;
    private String high_to_low;
    private String filter;
    private String min;
    private String max;
    private String search_by_brand_name;
    private String select_brands;
    private String none_selected;
    private String selected;
    private String apply;
    private String add;
    private String off;
    private String add_more;
    private String enter_quantity;
    private String quantity;
    private String reviews_and_ratings;
    private String similar;
    private String highlights;
    private String ratings_and_reviews;
    private String your_rating;
    private String rate_and_write_a_review;
    private String enter_your_review;
    private String add_review;
    private String view_all_reviews;
    private String all_reviews;
    private String out_of_stock;
    private String search;
    private String products;
    private String similar_products_of;
    private String available_variations_for;
    private String empty_category_product_title;
    private String empty_category_product_description;
    private String no_more_products_available;
    private String max_viewed_products;
    private String shop_by_category;
    private String all_categories;
    private String buy_now;
    private String search_empty_title;
    private String search_empty_description;
    private String max_viewed;
    private String featured_brands;
    private String write_a_review;
    private String created_at;
    private String updated_at;

    public int getProduct_label_id() {
        return product_label_id;
    }

    public void setProduct_label_id(int product_label_id) {
        this.product_label_id = Label.validInt(product_label_id);
    }

    public int getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(int language_id) {
        this.language_id = Label.validInt(language_id);
    }

    public String getLatest_products() {
        return latest_products;
    }

    public void setLatest_products(String latest_products) {
        this.latest_products = Label.validString(latest_products);
    }

    public String getRecently_viewed_products() {
        return recently_viewed_products;
    }

    public void setRecently_viewed_products(String recently_viewed_products) {
        this.recently_viewed_products = Label.validString(recently_viewed_products);
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = Label.validString(sort);
    }

    public String getSort_by() {
        return sort_by;
    }

    public void setSort_by(String sort_by) {
        this.sort_by = Label.validString(sort_by);
    }

    public String getNewest_first() {
        return newest_first;
    }

    public void setNewest_first(String newest_first) {
        this.newest_first = Label.validString(newest_first);
    }

    public String getAscending_order() {
        return ascending_order;
    }

    public void setAscending_order(String ascending_order) {
        this.ascending_order = Label.validString(ascending_order);
    }

    public String getLow_to_high() {
        return low_to_high;
    }

    public void setLow_to_high(String low_to_high) {
        this.low_to_high = Label.validString(low_to_high);
    }

    public String getHigh_to_low() {
        return high_to_low;
    }

    public void setHigh_to_low(String high_to_low) {
        this.high_to_low = Label.validString(high_to_low);
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = Label.validString(filter);
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = Label.validString(min);
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = Label.validString(max);
    }

    public String getSearch_by_brand_name() {
        return search_by_brand_name;
    }

    public void setSearch_by_brand_name(String search_by_brand_name) {
        this.search_by_brand_name = Label.validString(Label.validString(search_by_brand_name));
    }

    public String getSelect_brands() {
        return select_brands;
    }

    public void setSelect_brands(String select_brands) {
        this.select_brands = Label.validString(select_brands);
    }

    public String getNone_selected() {
        return none_selected;
    }

    public void setNone_selected(String none_selected) {
        this.none_selected = Label.validString(none_selected);
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = Label.validString(selected);
    }

    public String getApply() {
        return apply;
    }

    public void setApply(String apply) {
        this.apply = Label.validString(apply);
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = Label.validString(add);
    }

    public String getOff() {
        return off;
    }

    public void setOff(String off) {
        this.off = Label.validString(off);
    }

    public String getAdd_more() {
        return add_more;
    }

    public void setAdd_more(String add_more) {
        this.add_more = Label.validString(add_more);
    }

    public String getEnter_quantity() {
        return enter_quantity;
    }

    public void setEnter_quantity(String enter_quantity) {
        this.enter_quantity = Label.validString(enter_quantity);
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = Label.validString(quantity);
    }


    public String getReviews_and_ratings() {
        return reviews_and_ratings;
    }

    public void setReviews_and_ratings(String reviews_and_ratings) {
        this.reviews_and_ratings = Label.validString(reviews_and_ratings);
    }

    public String getSimilar() {
        return similar;
    }

    public void setSimilar(String similar) {
        this.similar = Label.validString(similar);
    }

    public String getHighlights() {
        return highlights;
    }

    public void setHighlights(String highlights) {
        this.highlights = Label.validString(highlights);
    }

    public String getRatings_and_reviews() {
        return ratings_and_reviews;
    }

    public void setRatings_and_reviews(String ratings_and_reviews) {
        this.ratings_and_reviews = Label.validString(ratings_and_reviews);
    }

    public String getYour_rating() {
        return your_rating;
    }

    public void setYour_rating(String your_rating) {
        this.your_rating = Label.validString(your_rating);
    }

    public String getRate_and_write_a_review() {
        return rate_and_write_a_review;
    }

    public void setRate_and_write_a_review(String rate_and_write_a_review) {
        this.rate_and_write_a_review = Label.validString(rate_and_write_a_review);
    }

    public String getEnter_your_review() {
        return enter_your_review;
    }

    public void setEnter_your_review(String enter_your_review) {
        this.enter_your_review = Label.validString(enter_your_review);
    }

    public String getAdd_review() {
        return add_review;
    }

    public void setAdd_review(String add_review) {
        this.add_review = Label.validString(add_review);
    }

    public String getView_all_reviews() {
        return view_all_reviews;
    }

    public void setView_all_reviews(String view_all_reviews) {
        this.view_all_reviews = Label.validString(view_all_reviews);
    }

    public String getAll_reviews() {
        return all_reviews;
    }

    public void setAll_reviews(String all_reviews) {
        this.all_reviews = Label.validString(all_reviews);
    }

    public String getOut_of_stock() {
        return out_of_stock;
    }

    public void setOut_of_stock(String out_of_stock) {
        this.out_of_stock = Label.validString(out_of_stock);
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = Label.validString(search);
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = Label.validString(products);
    }

    public String getSimilar_products_of() {
        return similar_products_of;
    }

    public void setSimilar_products_of(String similar_products_of) {
        this.similar_products_of = Label.validString(similar_products_of);
    }

    public String getAvailable_variations_for() {
        return available_variations_for;
    }

    public void setAvailable_variations_for(String available_variations_for) {
        this.available_variations_for = Label.validString(available_variations_for);
    }

    public String getEmpty_category_product_title() {
        return empty_category_product_title;
    }

    public void setEmpty_category_product_title(String empty_category_product_title) {
        this.empty_category_product_title = Label.validString(empty_category_product_title);
    }

    public String getEmpty_category_product_description() {
        return empty_category_product_description;
    }

    public void setEmpty_category_product_description(String empty_category_product_description) {
        this.empty_category_product_description = Label.validString(empty_category_product_description);
    }

    public String getNo_more_products_available() {
        return no_more_products_available;
    }

    public void setNo_more_products_available(String no_more_products_available) {
        this.no_more_products_available = Label.validString(no_more_products_available);
    }

    public String getMax_viewed_products() {
        return max_viewed_products;
    }

    public void setMax_viewed_products(String max_viewed_products) {
        this.max_viewed_products = Label.validString(max_viewed_products);
    }

    public String getShop_by_category() {
        return shop_by_category;
    }

    public void setShop_by_category(String shop_by_category) {
        this.shop_by_category = Label.validString(shop_by_category);
    }

    public String getAll_categories() {
        return all_categories;
    }

    public void setAll_categories(String all_categories) {
        this.all_categories = Label.validString(all_categories);
    }

    public String getBuy_now() {
        return buy_now;
    }

    public void setBuy_now(String buy_now) {
        this.buy_now = Label.validString(buy_now);
    }

    public String getSearch_empty_title() {
        return search_empty_title;
    }

    public void setSearch_empty_title(String search_empty_title) {
        this.search_empty_title = Label.validString(search_empty_title);
    }

    public String getSearch_empty_description() {
        return search_empty_description;
    }

    public void setSearch_empty_description(String search_empty_description) {
        this.search_empty_description = Label.validString(search_empty_description);
    }

    public String getMax_viewed() {
        return max_viewed;
    }

    public void setMax_viewed(String max_viewed) {
        this.max_viewed = Label.validString(max_viewed);
    }

    public String getFeatured_brands() {
        return featured_brands;
    }

    public void setFeatured_brands(String featured_brands) {
        this.featured_brands = Label.validString(featured_brands);
    }

    public String getWrite_a_review() {
        return write_a_review;
    }

    public void setWrite_a_review(String write_a_review) {
        this.write_a_review = Label.validString(write_a_review);
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = Label.validString(created_at);
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = Label.validString(updated_at);
    }*/
}
