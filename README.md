# Тестовое задание для Skyeng

1. [Task Description](#task-description)
2. [Build Instructions](#build-instructions)
3. [Libraries](#libraries)

## Task Description

Реализовать приложение для поиска переводов слов в словаре, состоящее из двух экранов:
- Экран, содержащий форму поиска слова и таблицу отображения результатов
- Экран, отображающий подробную информацию о слове (текст, перевод и картинка, остальные поля по желанию), открывается по нажатию на ячейку в таблице с результатами поиска
Загрузку данных о словах производить при помощи публичного API, документация находится здесь: https://dictionary.skyeng.ru/doc/api/external

Требования:
- Для Android использовать Kotlin
- К коду приложить Readme, где коротко описывается как собрать и запустить код
- Результат выполнения загрузить на любой Git-хостинг и прислать ссылку, zip-файлы и прочие форматы не принимаются
- Использование сторонних библиотек разрешается, в этом случае в Readme нужно указать зачем она используется
- Приложение должно корректно обрабатывать повороты экрана
- К приложению должен быть написан хотя бы 1 юнит-тест
- Реализованная функциональность должна быть максимально близка к production-состоянию
- UI приложения должен соответствовать гайдлайнам платформы  

Наш стек: Dagger 2 + Rx + Kotlin + Realm. Архитектура MVP.

## Build Instructions

Pre-requisites:
* Android SDK
* ADB
* To run command line tools, you'll need to configure `JAVA_HOME`
* Make sure that your phone connected to computer and in developer mode (with permission to install via USB)

1. Clone or Download the repository:

  ```shell
  git clone https://github.com/alexyndrik/SkyengTest.git
  ```

2. In directory with project build APK:

  ```shell
  ./gradlew assembleDebug
  ```

3. Install APK:

  ```
  adb install app/build/outputs/apk/debug/app-debug.apk  
  ```
  
## Libraries

* Dagger - dependency injection
* Retrofit - network
* RxJava - async network data
* Glide - load images