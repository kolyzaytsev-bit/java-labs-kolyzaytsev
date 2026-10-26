package edu.course.lab03;

public class PriceCalculator {
    private final DiscountPolicy discountPolicy;

    public PriceCalculator(DiscountPolicy discountPolicy) {
        if (discountPolicy == null) {
            throw new IllegalArgumentException("DiscountPolicy must not be null");
        }
        this.discountPolicy = discountPolicy;
    }

    public double calculate(double price) {
        return discountPolicy.apply(price);
    }
}