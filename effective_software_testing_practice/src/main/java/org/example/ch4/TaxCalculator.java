package org.example.ch4;

public class TaxCalculator {
    
    /**
     * 세금을 계산한다.
     * 
     * @param value 세금을 계산할 값. 값은 양수여야 한다.
     * @return 세금 값. 값은 양수여야 한다.
     */
    public double calculateTax(double value) {
        // 사전조건
        if (value < 0) {
            throw new RuntimeException("Value cannot be negative");
        }

        double taxValue = value * 0.2;

        // 사후조건
        // 유효하지 않은 값을 반환하는 대신, 예외를 던진다
        if (taxValue < 0) {
            throw new RuntimeException("Tax value cannot be negative");
        }

        return taxValue;
    }
}
