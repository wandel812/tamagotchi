# Тамагочи
### Инструкция по сборке
- unpack the archive 
- from its directory run `mvn clean package`
-  then `cd target`
- `java -jar tamagotchi-1.0-SNAPSHOT.jar`

### Описание
* У питомца есть шкалы (изменяются от 1 до 10):
    - Усталости. Восполняется сном. Если шкала достигает отметки 10 питомец
    засыпает на 20 минут и затем просыпается со значением 1 (sleep).
    - Общения. Восполняется лаской и заботой (кнопка pet). Пополняет 4 пункта шкалы
    - Голод. Когда голод достигает значение 10, то через 20 минут питомец
    умирает (die, goToBetterWorld).

* Шкалы изменяются каждые 20 минут следующим образом:
    - Общение уменьшается на 2.
    - Усталость увеличивается на 3.
    - Голод увеличитвается 1.

* Через 3 часа питомец взрослеет.

* Кнопки:
    - При нажатии кнопки pet. Питомец радостно прыгает (jump) и восполняет шкалу общение.
    - Кнопка feed. В левом нижнем углу размщается еда, питомец идет к ней и ест (eat)

* У питомца меняется настроение. За подробностями остылаю в getPetOccupation класса Pet
в пакете model



