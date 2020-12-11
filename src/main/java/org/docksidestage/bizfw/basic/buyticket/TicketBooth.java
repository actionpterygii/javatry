/*
 * Copyright 2019-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author jflute
 */
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int MAX_QUANTITY = 10;
    static final int ONE_DAY_PRICE = 7400; // when 2019/06/15
    static final int TWO_DAY_PRICE = 13200; // when 2019/06/15

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========

    private int quantity = MAX_QUANTITY;

    // finalは、その変数()はもう代入できひんってやつ。(もちろんそのメソッドではいじれる)
    private final TicketQuantity oneDayQuantity = new TicketQuantity();
    private final TicketQuantity twoDayQuantity = new TicketQuantity();

    // チケットが２つそれぞれにあるとき、共通化したメソッド(NDay)では
    // oneDayQuantity類を引数で受け取った場合はデクリメント(売れたから減らす)できないので
    // そういう型を作ってそれのメソッドでデクリメントすることで直触りができるのでそれで
    private static class TicketQuantity {
        private int value = MAX_QUANTITY;

        public void decrement() {
            // イン/デクリメントは前置が安心
            // 後置はそこでの処理の後にイン/デクリメントされちゃう
            // 単体でかくならどっちでもいい
            --value;
        }

        public int getValue() {
            return value;
        }
    }

    private Integer salesProceeds;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth() {
    }

    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========

    public enum TicketType {
        // enum要素という
        ONE_DAY(1, ONE_DAY_PRICE),
        // コメントが有るとフォーマットで横並びにされない
        TWO_DAY(2, TWO_DAY_PRICE);

        // ほかここから下はclassと一緒の呼び方

        int days;
        // インナークラス(クラスの中のクラス(enumもそんなもの))のprivateはその外のクラス(実質このファイル)からはふつうに呼べる
        private final int price;

        private TicketType(int days, int price) {
            this.days = days;
            this.price = price;
        }

        public int getPrice() {
            return price;
        }
    }

    public int aaa() {
        return TicketType.ONE_DAY.price;
    }

    //???
    // 考えると考えることが多すぎるのでもうここで締めます

    public TicketBuyResult buyOneDayPassport(int handedMoney) {
        TicketType ticket = TicketType.ONE_DAY;
        Integer ticketPrice = doBuyNDayPassport(handedMoney, ticket.price, oneDayQuantity);
        int change = calculateChange(handedMoney, ticket.getPrice());
        return new TicketBuyResult(new Ticket(ticketPrice), change);
    }

    public TicketBuyResult buyTwoDayPassport(int handedMoney) {
        int price = TWO_DAY_PRICE;
        Integer ticketPrice = doBuyNDayPassport(handedMoney, price, twoDayQuantity);
        int change = calculateChange(handedMoney, price);
        return new TicketBuyResult(new Ticket(ticketPrice), change);
    }

    //???

    //    public TicketBuyResult buyPassport(int handedMoney) {
    //        
    //    }

    private int calculateChange(int handedMoney, int price) {
        return handedMoney - price;
    }

    //パブリックメソッドはなにもしなく、プライベートメソッドが実処理をすることが多いので
    //プライベートの方↓には`internal`や`do`をあたまにつけたらよい

    // `N_DAY_PRICE`、定数の上のやつを踏襲せず、定数じゃないものはローワーキャメルでいこうね

    private Integer doBuyNDayPassport(int handedMoney, int nDayPrice, TicketQuantity quantity) {
        if (quantity.getValue() <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        if (handedMoney < nDayPrice) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        quantity.decrement();
        if (salesProceeds != null) {
            salesProceeds = salesProceeds + handedMoney;
        } else {
            salesProceeds = handedMoney;
        }
        return nDayPrice;
    }

    public static class TicketSoldOutException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketSoldOutException(String msg) {
            super(msg);
        }
    }

    public static class TicketShortMoneyException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketShortMoneyException(String msg) {
            super(msg);
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getQuantity() {
        return quantity;
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }
}
