
# 1. 개요
콘솔 기반 키오스크 애플리케이션입니다. 메뉴 출력, 장바구니 기능, 주문 처리 등 기본적인 키오스크 기능을 제공합니다.

</br>

# 2. 기능
- **메뉴 출력**: 판매 중인 메뉴를 카테고리별로 선택해서 출력합니다.
- **장바구니**: 장바구니에 메뉴를 추가/제거합니다.
- **주문**: 장바구니에 있는 품목들의 총액을 계산하고 할인률을 적용합니다.

</br>

# 3. 기간
24/11/25 - 24/11/27

</br>

# 4. 만든 사람
### [cnux9](https://github.com/cnux9)

</br>

# 5. 실행 예시
```
[ MAIN MENU ]
1. Burgers 
2. Drinks  
3. Desserts
0. exit      | exit
0-3 사이의 수를 입력해주세요: 1
[ BURGERS MENU ]
1. JustBurger      | 6.9 | 무엇을 바라셨습니까? 버거는 버거입니다         
2. WhiteFetaBurger | 8.9 | 화이트 페타 치즈가 듬뿍 들어간 신메뉴         
3. CheeseBurger    | 6.9 | 영화 더 메뉴의 그 맛 그대로인 치즈버거        
4. ChickenBurger   | 5.4 | 우리가 어떤 민족입니까? 치킨의 바삭함이 그대로인 버거
0-4 사이의 수를 입력해주세요: 
```

</br>

# 6. 트러블 슈팅

### 문제 - 클래스의 역할 분담
- 콘솔 기반이지만 기능이 많아지면서 클래스 간 역할 분담이 어려웠습니다.
- `Kiosk` 클래스에 많은 메소드가 집중되면서 복잡해지고, 출력과 입력 처리가 뒤섞이는 문제가 발생했습니다.

### 해결 방법 - Printer 클래스
- 출력을 전담하는 **Printer** 클래스를 분리하여 역할을 명확히 분담.
- 다양한 상황에서 유연하게 출력하도록 설계.
- 입력 유효성 검사는 **Kiosk** 클래스의 `nextInput` 메소드에서 처리하여 구분.

</br>

# 7. 클래스 구조
- **Kiosk**: 애플리케이션의 메인 로직을 관리하며, 사용자 입력과 데이터 흐름을 처리.
- **Printer**: 출력 전담 클래스, 모든 사용자 인터페이스 관련 출력 담당.
- **Format**: 콘솔에서 문자열 색깔을 설정.
- **Menu**: 메뉴 카테고리와 항목 리스트를 제공.
- **MenuItem**: 항목 정보를 제공.

</br>

# 7. 실행 관계
```
Main.main
 └── Kiosk.new
      └── Kiosk.start
           ├── Kiosk.openMainMenu
           │    ├── Printer.printMenus
           │    ├── Printer.printOrders
           │    └── Printer.printCart
           ├── Kiosk.openOrderScreen
           │    ├── Printer.printCart
           │    ├── Printer.new
           │    ├── Printer.print
           │    ├── Kiosk.openDiscountScreen
           │    │    ├── Printer.new
           │    │    ├── Printer.appendNumber
           │    │    ├── Printer.appendData
           │    │    └── Printer.print
           │    └── Kiosk.openDeleteScreen
           │         ├── Printer.printItems
           │         └── cart.setItems (Stream API)
           └── Kiosk.openSubMenu
                ├── Printer.printItems
                └── Kiosk.openOrderCheck
                     ├── Menu.new
                     ├── Menu.add
                     ├── Printer.printItems
                     └── cart.add
```
