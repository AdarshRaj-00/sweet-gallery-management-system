package com.sweetshop.service;

import com.sweetshop.model.Role;
import com.sweetshop.model.Sweet;
import com.sweetshop.model.User;
import com.sweetshop.repository.SweetRepository;
import com.sweetshop.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final SweetRepository sweetRepository;
    private final PasswordEncoder passwordEncoder;

    public DatabaseInitializer(UserRepository userRepository,
                               SweetRepository sweetRepository,
                               PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.sweetRepository = sweetRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Seeding Users
        if (userRepository.count() == 0) {
            User admin = new User(
                    "admin",
                    passwordEncoder.encode("admin@2005"),
                    Role.ADMIN
            );
            User user = new User(
                    "user",
                    passwordEncoder.encode("user123"),
                    Role.USER
            );
            userRepository.saveAll(List.of(admin, user));
            System.out.println("Demo accounts initialized: admin/admin@2005 and user/user123");
        }

        // Seeding Sweets
        if (sweetRepository.count() == 0) {
            List<Sweet> initialSweets = List.of(
                new Sweet(
                    "Gulab Jamun",
                    "Milk Sweet",
                    350.0,
                    10,
                    "https://i0.wp.com/www.chitrasfoodbook.com/wp-content/uploads/2016/10/gulab-jamun-using-mix.jpg?w=1200&ssl=1"
                ),
                new Sweet(
                    "Rasgulla",
                    "Bengali Sweet",
                    300.0,
                    0,
                    "https://www.bakeryhut.in/upload/pro/product-featured-469.jpg"
                ),
                new Sweet(
                    "Kaju Katli",
                    "Dry Fruit Sweet",
                    900.0,
                    6,
                    "https://www.cookwithmanali.com/wp-content/uploads/2015/11/Kaju-Katli-Diwali-Recipe.jpg"
                ),
                new Sweet(
                    "Rasmalai",
                    "Milk Sweet",
                    450.0,
                    8,
                    "https://cdn.uengage.io/uploads/28289/image-1FL1DA-1723034440.jpeg"
                ),
                new Sweet(
                    "Motichoor Ladoo",
                    "Festival Sweet",
                    280.0,
                    15,
                    "https://cdn.uengage.io/uploads/28289/image-6QSU48-1739196193.jpg"
                ),
                new Sweet(
                    "Soan Papdi",
                    "Festival Sweet",
                    250.0,
                    12,
                    "https://ganguram.com/cdn/shop/files/soan-papdi.jpg?v=1757319687&width=840"
                ),
                new Sweet(
                    "Sandesh",
                    "Bengali Sweet",
                    400.0,
                    9,
                    "https://cdn.uengage.io/uploads/28289/image-W3GZB8-1752055649.jpg"
                ),
                new Sweet(
                    "Peda",
                    "Milk Sweet",
                    380.0,
                    20,
                    "https://img.freepik.com/premium-photo/kesar-pedha-peda-is-indian-traditional-sweet-dish-made-from-milk-khoya-saffron_466689-71764.jpg"
                ),
                new Sweet(
                    "Murabba",
                    "Sweet",
                    150.0,
                    50,
                    "https://5.imimg.com/data5/SELLER/Default/2020/9/BW/CQ/JQ/23369051/murabba-2-1000x1000.jpg"
                ),
                new Sweet(
                    "Almonds",
                    "Dry fruits",
                    850.0,
                    99,
                    "https://www.marthastewart.com/thmb/DC8AOqnOjErmTiFgWBxIQLMig2I=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/ms-almonds-in-bowl-getty-dbf3bf1a968b405d9d45de9d4b910709.jpg"
                ),
                new Sweet(
                    "Milk Cake",
                    "Milk Sweet",
                    540.0,
                    12,
                    "/images/milk-cake.jpg"
                ),
                new Sweet(
                    "Jalebi",
                    "Festival Sweet",
                    200.0,
                    30,
                    "https://www.bing.com/th/id/OIP.BXO-6LwGJbL1UZr9_Ojz7gHaE7?w=193&h=135&c=8&rs=1&qlt=90&o=6&dpr=1.1&pid=3.1&rm=2"
                ),
                new Sweet(
                    "Balushahi",
                    "Festival Sweet",
                    320.0,
                    15,
                    "https://www.bing.com/th/id/OIP.6ra1PSbBf2oOsj-oHPlLGwHaHZ?w=193&h=192&c=8&rs=1&qlt=90&o=6&dpr=1.1&pid=3.1&rm=2"
                ),
                new Sweet(
                    "Kheer",
                    "Milk Sweet",
                    180.0,
                    25,
                    "https://www.bing.com/th/id/OIP.3SKWyW68O51TFpLglQPiKgHaLH?w=193&h=290&c=8&rs=1&qlt=90&o=6&dpr=1.1&pid=3.1&rm=2"
                ),
                new Sweet(
                    "Gajar Halwa",
                    "Festival Sweet",
                    400.0,
                    18,
                    "https://www.bing.com/th/id/OIP.V1ofA0I5vXN1VHHcTJQA9gHaEK?w=193&h=135&c=8&rs=1&qlt=90&o=6&dpr=1.1&pid=3.1&rm=2"
                ),
                new Sweet(
                    "Besan Ladoo",
                    "Festival Sweet",
                    300.0,
                    20,
                    "https://www.bing.com/th/id/OIP.MMUTOxHPCzSxLLUg0mRV2QHaF6?w=193&h=154&c=8&rs=1&qlt=90&o=6&dpr=1.1&pid=3.1&rm=2"
                ),
                new Sweet(
                    "Chom Chom",
                    "Bengali Sweet",
                    450.0,
                    14,
                    "data:image/webp;base64,UklGRkQjAABXRUJQVlA4IDgjAADQmACdASppAacAPp1Cm0klo6KhLZRcoLATiUAaGUp7J+t3iujR477144l7f13m32M/THzi/U35vfNl07/esLSUa5bTfyuH3Eytr/4ngX8/dRTFTuFwEd2BPCWOqAvjsaaH29cXVezvkfiEDz3YX5HHmuS0GsUbTB73hANIXACDokRMe2d0Uta75B/2HQt4jVi8HGS9uCoqv+qeGRC7xisgQCwrLqxs9CtXTIil0TfjzB5+JpGnywuWiXyE0OUVZ8FIFeymxFl9NBWoxuPDjU73MzpC4fyGxuyDWoJ0OsKTC20CZb+uaclEL0jyL9Lbhy5n56fR5Ve9mfgGRrT23A335kBRyxtLHUdJd+8u+AV3M8aJ7KcVvogwB5nqNCOCb2csnDNokbqJ4kDys8AcNDH+zp9/+GbUSJL8gDq5g/fi98JGK5m6vu2eRYxoNEGsZAsHENsXe2PmICVNxsWdsGp/akUmwGXn5DXYIdJXT1X3esO/+Q04Jbyme8KhgCwgGy2RNzp4ee3mMpsHWXOFIyTpKkaiZq4POdFWR/9H4ecEmfMlYPS41JQuQHowraEvxioqfxkdNlaRMbsauNUqPrNshR76JjGCTqMRTb0uQn0H+g0AFZEoX/pNoeVLeTT9LABWh+xPKh5BVf8O6KHf/Vzdqj7AcNEd3BIWy05+UJgtRCTkxPLSOsOeHGUxvqL6V/W3N64t+Y6eYrAsObQ5w/o1lzit6SiSsVwraQdJ+C5xOZke1T4aj2j09wVLGD4yNIaB/iZ3dMIp3Uzq1Q9WAVUeKrJTyy4S9S8TCYKvYVO3Qam+lvVWCUrSppAP336uhxfZT8Q9c4FMCQAC1dUK+C/7pmo2koJiRShD3iC62mm5iU2nLeZC94K2POMn8zw830qe5o0QGzjhQ3eS9JRVnJwd0JYUq3QCjD7fprRq2sqjinNCsykihL0Gucl3wboKsM+nOEjhMV/WlGZNATBOGLXowlPvtkihaT1VZTCyEYHEs+tJlE3odtAzCnYqn3uGDyL9q9722hcg78jJ2YyzmhEKfsB6/olaHn9O9W+E0Y5T/uvnR2sb4I2TZmsSpS5XNjeNm+nlZ0SxGScjoJqeWfEFlSF6pRl29lf8TVvV4tpPBKjnwysV0xl24d9oE2IOx7S870XofRlaVmNOJuhH7athhOueacn+G23rgn34CYvRHrPNm8MrQxPdLMCtbhvdsoUDAdnUwNW0nOCGXKDasu51wmVVF1QXB305ju91/qFU0sSXKiV4m7mDldo5mj6XcObxfCj17cuuGCcnRn/NX67gWp2bBpD/Yr53jbupi/yZRuQyMFid13c/HKPmMFS0BnfBVYogBC0JjRKRggBWOZEzs9jLz2ACRtqoO2Cy5Ov8Jj5IEAwH2ZEiI2KUdcGQsQyPcpjznWxtd8ixYD20OfUN1LE83wbp376rg+1YSgWj3s8xq+s93MfXd15nGHDMWMDM4NFVfbRmHpBEDo2fP891ZgyN9Aqs2rsbZxkWkBD+ruguCJ92eZw7+3WjDcKurk1EGOar/p1Q+Ldhwk0EyvdmLRTifHSQ11Xpl/rE8GwdApr1R43P18B6zJmljsyn2gSa77GH/2PLHdnmVbjf/M6aM2FtVTjyBCdcZ75q3AyAAP789AANwKRaBvY8nPn7sCoRq6Re2oFygprMdJAAQUiM8pyMcx056aozegHXpJf8gZBNW+v5Kb6VRQAQhM63l+zv288figIFt9E59/wApUFR2akM31fFyqFlHFU07+tL4mbfR837ILX38oCH/mhihyFhWcBjiZudDbFVxdwjoAGzmwctkfFLtVi35sAGCKVY34udQs1cCp9Qwco8juApvL7kRTjZX/XUHqTat3AAAAAQoOUSwVD8jYqO5An706tvwtZ7BbqJpi1RaxjZXxjfKXUh1ztxMstII34S2IzyYLQWhGlBsezhAVVMMiqJf00oNBSnZk+TNSmLYyqWvKh77B6iZQUwE7ZRNczT1+8apGzy7469Ct8AZFA5fyHLCAghPmBWL8f3QYcZXAmQGm9hgrfsdln2CQ7v2hUIKHqVg/Apex5UQt8o3bld2pezu/oGNc00oeUsHwXYsaLJcjx5ruw1XeGutmfhCaITmuM/A+U+2SJH5hXt5aWP6zkJQi6McH7fwTFSJhtO0euG3+ajVuxNabK/yfvLF4MuaZ4chmeK50Uas/2QmP5/hg+VyTcD3YzOPG4ArJ6f8Y5u+JOYUVs2qr5wiawJpEoZOh6jEKuCel3W5bAydE9I86UVid9/JxQbvthOjnDFxxtBAAb89tzieDGH43hG3BCGScL5ajen4lm/sQSQaUKZ42FLKm66qWCSsvYI+tEbJpNpx7QZuWplfY/0psZ+92Biv0lON1FtLMH0JsJB8NQbnykfcvtHQUES0OUguCAADr5+y6xV6aLw/d+2bAwL0C5FiHZkNC3YJ8MpG2T+XXRCtDST0Xnj4QTZKCS0DR3EEBooge34POpvYspm9cbu8+yX1eG2AGCNxKsWQ9SPDPFLEj9KOCPAAr43nMRX/2xNyQ/MjdGl4Al2tkXBFxnwKC75BBnt46K1giyYxYv7NBqrb5sumKXt8t1MO9dyPcWU9XfOifBx+w/+GTlD1a3qlU6cEGixXCF8U0zJxCXoNtsOS6JpUZMpC0UMbaSojGKXj98eaoJdbs7+BBMix1HKXYOkIZw6ByUzpcXGAcdWfjP45yPR+GQ9KR3mrqajO0Gn53rBOrjhpz2nsJGru2tR4Tg/01iG9WvG9zUgYMO+KcxN7NeKa2XxASC3GhfXIBGHdBUQSwIA4Fr/gfYJXf3jxbHgIOVZRLek5Wf2tpHwlyC/FzXTrsKhmqrG9ZZ4Hj8IoMeiPrQ5nrJkZnTCQZgUK3QAkAIxcia1H323mrMxmkK5Usc7TFZ/tGIm8lODKsm5Jd+FbaIwLcpoiRvffN1XV9HfgnQiObser4Gyqww40U1dVCeCDC2J0/Q5umSjALDNaGWEaV+F/L2H1N2iwHCDDO9z0qMG7xcwJ1dnuXsG2aNfsWN2aEpTsiUTiPCd5WviXDMFHvpkOzEHYSSRsyg6BMRetwu17wigkQfwHavt42seRyR3W2n9hkyzHb0hkCcHVBEYK6behEKs/RNi1J+0nfaGlSlvUMQsrJTy39K5lDZL97xc34dlJuYI8F1NmEwqhhX8uWXh+nlpGFA+iIReiqQEY129WbZTc2fRie8tQpEi10rthehqI0AMvKZyNC1JSpBUCaQQWc5b5T4gxmWqV0HxFwD1qRaTp/h0PfB6S4AVmy3svOxvn2/L4QTig8Ac/7dGjJELsBPudpiQJPYT7iQkLhAn6jXgVxEyrDjYnx9xvYPCvdTjkY5qdumw24dSQwL0fg94kGyUMM0mBTB4PbVggdq1Obo6CA6IkOAXMT4h5PfBtjDR2IMxNzQja9g5of1KyEWxrlOdibIxHfM+zvfdPq289EE1n7rsXpKcKah0vlqrPuf1UPLon+RggaWPAJuveU8SzxbLDlgwucrWQl9QMA95PZkpl973s2wFyqmo0fbfUy8h9JNfcK06+jaUk/cvkCPZt45V0Z61zAweuZ3aEd8RxWXltBVzmu5OAuEXutCxHBr55+rrhQ+Vh5XtZSHAD+MnFEymm34EUTsz7ooX2MFocbZgxMx8Sv2z888nABe/falAVSuhW2NTl+nCXxk98Jk1uM+K/68WZPszyJi5xwRABByK6RJTnHi56z1y3AR8/oT4CKEPTtW09573Sge8VgidV1BM2UjcYuSkTNCPbxVd+LzdL+JF1mJiRUb7Xtsg9jG2FeipBuDv0FZ8yUghBFj0YhvMsmCgzS/gMGMA2Uyia1cpaLW3XcrAhBf1WEDw4MOJ8KfQno/dwzhhyAqLYoZqpVRDS0VQEAj8j15jQUDNiuNCEvR4VmuxaPkb4SV3d+g0TnBSyidxZgulSB5W0FisNOiFYEGsCyoLtqCo+81BkV5/3dmJZYBK7c/Uso2WEiJggqgqpU3RtdBseBGIO1mxMvpOhSTpctkYnK+VTzHqlFl4kP+/uiTnWoymnWkIuWM4n93FfMU6tXlgx4VZ/O7vn29bDXeHut0PndLX4OMH7XefHFCPOZ9bcdvardvoDg6h1Qx9w7Toe2AaB2lPrm4jHbHodvAaA2uTn936tpJKL7Hq65P8tnMQTcxxWs0N2ngZDhbuHqpul/ulw4RDQPcHHA9Qn32+5bmdFUwIs27aXKRsJOlKFx8kQZZws4a66AvDLNITQrkGnp1PKsjr5mTUITBtyDXYGLKu1Zn3Ji4O84ACNYurOZh65QpFdilAAfaXr2hvbnzPc5XbzdXOm49ePuRkb4A0vLfJDZBLbPynQTKSGncz891sVP5WPYKKf2o2QLosiujJf82AQcv5sxj6tKJVBpPdcFy77FP8hZv6yiOEm7BHUgBPcglk44X9alcz6zuI4iKLWrCGwOE6rstVyhX2S6Ly0/euQCjx+50hA0ImSjXm5Rcqdoe0LdwlGklFL8ZAYfipgZjcycHrhMO8Csv/99VrRyDXx9ogNiYDVGTDWMcfolrbroUVlOyTvTeTjp+FimNun3x2XnKKamENcC3FgD7kFC69CwDhV807GDwTz1/M/8JjqMkcZrJC9fIB3bU4kZr8gdgb2sLVbJ53lCeyGxZo/bNgyw67QCL+KzPg7wlP47X03J1IyxZDCb7QgBu790bt1ro7AuoQZH0wX2rTslpqYjzsL6D/wpl/JYTa+Ir5gG639ar/RBMSzQAwVV0vEo2cfijIIJIQKiVOD+w9G67fm0WWAFb4dx6fwDhyJfgnvpilEYms2H0cxqDZmATfPE2T59rHEvL0pYHhS9NRISI5xqGgWl2tdbza9aFDKB0/v2wAP7lb/m7SHnaUMcq2bR22hUefSeZA4my9qC0NXSitJJb+pMi7LLRosyJbRAxQsbVKKdPSrRs0dhPFa+BxX9EhREqBwrx07APwL9uXf5GwZhy4H+Ah7E56Awxdu5hf0IhCVDO+bf9wogX8fM85kKsWrgWkByo0mldZNjTPVSSP9gNWWXpvIo7ovwpjDZ0F2hc0Ob3Iw8BmRtrQ5lK97JovoXCv/PjbBBucidxjqjLhFRD9tSi3gDKGWg9cgqu0dYCtgXpW63HT/HxysJ2ZecO/3X6KCcd0D5eSgJab93iGg0JYlCdSSoo/hCz7k21O/7HX1pEZi4FrDG/kLno6A6ZMgYq3CHxxsAhFZENQAbLf7A798FqkgEzBh7D/CWeXTs8Mn3ln5rExD4OOJyxBZyNxX17gLAt67cqfJAT12tLF72MTWl0aLg+Rs24OThxqEfwcR2QBfCsKvo8Jw/qaxnkNiROD8qO34h0B9kBm2Hkn6gmtQykiSBhfdNVBVpKGQ8AO45U0SkrBPnlwPHxjAUApjoN3NeIbvv4BCiqf0VzPBHjG0qYIF+xo4eysfFPFH9ci5IV5T2UiYOBZrIPCZSQvPwv5rTvb2wkYoTpAC9eovH2xjH7zIhXr6Lo2TENQcr6w1O2vav9BBK9J8ByhRmMHwwEpgkgbYdO1cnSvimMH+duFfDvFcH76x8SGrxYsJ4by4sJ9gASKiHWhbBSDKQ94AIlsgu67MHZd72P8Jfvf9zsyylHRpZx9kkI21pssx1120WImfi+RpmURtLc2T1CLl7s9Zk3zOhw4mKguqx0b9BOX5qmzS6MCuwiu1cPR6ZnMIXasmfXF1yGRGw01W24FFF4H5rAuDvSXRrBYxk1yzvwa8Fa38bJQNj8RpyYGpm3UHd3SrdmqTFKFvZept0pd0vN5uIsfBNfDqzhGF0SF7fs08GcSdxwWB+23rqeWk9s4c6SHEyzdjjsYtq6QD0/dkck9UGPeLUj72eNAMNNib3dIsr2WWeR8wqnNEDJlRjV1gXRbXEUlY3C00TudYjX/MYgTR1dB+raFqtzqZqYMF1P970jZoDl/iF2EcAOQye30tXLEL6yQPhrNyXzoj6rqWB7NIwutiOko+uDyaPoKOLSIJA2KgBePt3iWYuUaXTzgd9vCkqTA4MVMNoaeg0mlXspTVEAnO70QUVR+MUzMAilclcKH3lFKWK6mlsSWZLEozijjD7msE17Oo1dT4ICitMIb2W+VegHaarAAHVnZKbOZ3ly1nvX1F1S2nLAXGaRyGQTkkd9Gr0wDjxglI7txCtyJ9SZUD+E/FHllCzSMrgT8swszNQmiwz28nf05IRHmDUng7V2HnkYEpVcvBO/dVNHpDhH9tdbVOOz9T0zDn5WuTWL346WmgbBdRxIdhWEKHEF7yeW//x2VhFT5bpsclryD/gSnkcj9LJP8KNQwfJLHJtYRxaa9a0TwUnJNjJdf05GK1c0krIe1SZ6PPgIuirAvzqFjWOAA4dWiJTi/2TC3OUzs+srB8IhqHmkrcNB5h6Zq9C+7xxf2NMMyG/23jJnHIf0h9vDogXdg6XfsZdXOCbofsGKAacM2wgQLkrlj9ulphB4QJRyjp6KiCHW6yTgLAKrGBXG6UT20+THfqiIKyu0rrRb1xwpfczRe2kXAr68ci3jg975YOmPvEPEpspWKbc8VErcL45tzNjZeTdcfMCuyO7X5QtKXcjMP/2NcKsHh3dafO3iTr35WrGqADY7mxcWM0Wx8PEiDRWhxvRYgZ8vu2GTw+o1bDCafxXW7TnUBDE7IuK5afsAaE807/F/4SEEcFuy+5XVxjv9HYUifzixHskunJQikPaLXidYQMQbcRGZrisu+DdPysNIHog3OfjoTIkGMt+Eq5tZY9GjxB5vKd2mogmki56rGtM5PAGWoNzlO2aSwE2gq1goi+E+MH15GIimMRegoD/1mqu/qkcRvTUEVNlUM5sbdkI4kemls3RcGeFQKzHoFcIHYnow4FLt0OtfJeHS8+mmJsXWbKoZGzid/RaNRvFxb4CJesucxwcSkD0EQ+gNrEaAaxaoL2DeO6XMLSHYghGVBT4hxZyJOtq07WfstzA86eccYrC81XLKVvIOWW2VimrSIvPYdQaehQT7yBNMTFQOpHHW7+IX+kjEyAoUgqwNfCNIlX8zeYAbUX0RvPgdcY+z/ZJ8p2puwpEHlcP6IYZt9urGjXoi79tlgWWJPyHCbJA2+6sDRY3xoe2bzH4LVpNkWn7qN5QTEB3NaOdI9UsvZ3hz+c85hY1yOx3G5+LvfetKQplhgoTgR8heIrntWNcqcaxEebz2GUnfsyJENIHOoX7mHXHpAewJI8TRL70lkn9qGrJgDHKFV0GMwFvkwyYFjaTztYbFqBU0eeWeNeVJCHc7GkItL/amGu+JeY+tT0TI1LtGp8IARO5b506wMCbQLxTzcsZqot7H2LDwuLdWRHCWtCp3C9V1mkvdDCz7361AxtiHCd1eVD2pF9jX11OW0XZbxrZPi1c+j/9sMxscoFpsHsfbUN/eo8+4k4giIEwwmKoMo8SBmE0Xtm1psQVmkTNByZzDUyrt40gnJNn+5qaZ9RxiBV1hfrh7WCfX91SdGYxtUrj0B0jLzv88BWpTbBVwAk39e8hkWkWn+e34EmmxeYxoR7xl7VTevauT/7B+vR6noZNDFrFftIjN1TRFiwmcedp4U5AC651KJuajmOnalD/sB55woA00VNFpW4sxypEm3DNIZMXzuYzweR07vXZ4VZOauHkG24zT7URCaQE2iX1W/CsXfrhgYOiR7ijjU3ddfZQrJLDnzPxql0HpuYq6zpFKwRYKzQFxGB3VoggxiIJGviTCR7zzn1AiB6Jk3pFal7huDUHAFp9X/61jQbfctkSwYxx/IK+E5FgeUx9fg6TR741IqNJKDHZ80UvRlJW5fU1DlRhXU6X5SAbPDfKKHmvFlNJmHtKhzFEH9H2rNDOl3F+y/E+R4bI5hZz4Jw0335204igCVsIk6HMzj6MW8O+TGtHOXq4GYPyo4Cs6nppueSDtNAqIe1sGOlVoRg5d1kLSvAMeHBbewypomdpSDXy2lOeSAwa9erB13tneWrV5WiRfs1+PH59hRQYpivh5ksAzYuvlQE2ncCJAImjfaRZw71VgKZRAqNoUMPQ5tstBS4QD3sXGwteMwUIPz/1JSufZsrBtiCI1JEFPg6UERvWZdiUgdDU+v3aJph2nqElO6kiIarsz7MR6zKVE5kx6SC25AZprKAAAm9GzF3LCGHW8L1HyI+HWNqn+Zk/XbtDZpM0i4aC5YiAdA731s0bWa26mMhuqZ7Old6iD0OwsGFTG/gXEVuF6i4ZWoUTsCCbtLFRK2hPUdJ8bCKi9hkDhVQKMMgzgc4ui+GFXP0NUIg5R0/hcSTyrxuQaf03sov1L3yVlWvW25+GF/KwNGF9U7ZpQksHj/V46C0nNlKYpiOQrfaClHqjkQvsgkS80vK4t2o8Pb39mLsB3TcUuuFi1MQWo6MHt+WT1Psh89XvlR5GHa/lhJ2b7P24Uv1N5AGmDpP7tuTkRxTLzOEnLGueZhkWBQMXNMly2tfhHV0fGWQ5HKtzV3e/fsfuZCHxM/a/zZuY2WkEoeAC+iiHgrFHra0lSJokcWUZh6v9qouLihIaIgIZgloRwpRToH9nHhYkepzjTkcbLGLGGxt/xVlUUYz6G7fHSDAFwglvvJrrYEDRnjVgMyalU9d7SkxkujIH+FbL2Go8F2QElZnOGkbcu4stcGuteqVOGo85JCaxbFITMFZg20CXLc554QWzWzf+iUlEq5E95O90zda3z8a+KJXIV5jhkRAsgFdbjIbaCVglGETGsFQeVihBD1Q47R8W3iQIKq724J0mOndkDrCho6hp7XoM4uCMjuS/LVTVpCJDXaDHt9RX9qzd833+w+qbwdmMQkWth+0f7o51gg7EgOqosXBcIlQEJw8PLQqe3RTeQBpg6+lBu6eJZcv8iu4+xzEUDNFFoeGpr3POHG7NtnE5ldm60ogdQQ7VTeAwPFwZVF4FQNP4+eBZNfZcGlF39e2T5IdKEFxBvMZCmcni+oAtPwPvPlo4VlgXPcGnWIpZfoPkriCGBoKJA1noYLxJkzl+pOGMqTZ5S4I1H80r/XZl+n1QadBbM4qQ8iYA2x7ntiQAgCoqxJhwAAAAGKJqoYStDedV91iXWh91sWY9aWre/3pBHfDCchGytA1Xy/9x+8omeFVEUxt2ZoRjrDKM58JxbmZvMycS/8XIgm4bfvfPfOOBeAPGeJz/P5KNIJPr6GDFZNsJefXPkHJCdFLUVd3ECr1qtZgehVpSy1YyEUamB3AhxRFRRGGUdr9ApfhVjPrWzhYrRgmGyAQomz5qI/Lk41PsLIGyPTsCJhvbHU3IM43A6EhEUIjptkdgd1sfClFtd650SEACbye1IH2dAo/37VYZDjXptS+ufGJdLtGH+t0mMxDVg9iCN4WLdVS1rcpHcA0wrIRl5hIlBp1DbnPEzi98c4fUjh6FJ437VnEVLUQ1bQ+aGVmSzs83HciCraUJm5qWORUUpeLWZVtBb80Eq5uGoM0CSAqyNzCtWnB82gOAfxhrO5jVCnSbZOQKC5BM2lcW0oQTGrTotLb1wACxH3cwi4j2W9eEAAAAA"
                ),
                new Sweet(
                    "Mysore Pak",
                    "Festival Sweet",
                    480.0,
                    10,
                    "https://www.bing.com/th/id/OIP.hQvvgsER_LHsj3IZ0j2NZgHaGI?w=193&h=160&c=8&rs=1&qlt=90&o=6&dpr=1.1&pid=3.1&rm=2"
                ),
                new Sweet(
                    "Coconut Barfi",
                    "Festival Sweet",
                    350.0,
                    16,
                    "https://www.bing.com/th/id/OIP.JtUsbHnhL4fxo9itIudq9wHaHC?w=193&h=183&c=8&rs=1&qlt=90&o=6&dpr=1.1&pid=3.1&rm=2"
                ),
                new Sweet(
                    "Kulfi",
                    "Milk Sweet",
                    150.0,
                    40,
                    "https://www.bing.com/th/id/OIP.Mpg3e266JbYzlPJDhNezNwHaGL?w=193&h=161&c=8&rs=1&qlt=90&o=6&dpr=1.1&pid=3.1&rm=2"
                ),
                new Sweet(
                    "Malpua",
                    "Festival Sweet",
                    280.0,
                    12,
                    "https://www.bing.com/th/id/OIP.4YFIwB9ahIQeNpB1VOAfgwHaE7?w=193&h=135&c=8&rs=1&qlt=90&o=6&dpr=1.1&pid=3.1&rm=2"
                ),
                new Sweet(
                    "Shrikhand",
                    "Milk Sweet",
                    300.0,
                    22,
                    "https://th.bing.com/th/id/OIP.WSY0pqy9CrSdAE4GiErZCgHaE7?w=301&h=200&c=7&r=0&o=7&dpr=1.1&pid=1.7&rm=3"
                )
            );
            sweetRepository.saveAll(initialSweets);
            System.out.println("Default sweets data seeded successfully");
        }
    }
}
