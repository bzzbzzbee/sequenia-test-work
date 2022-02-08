# sequenia-test-work

Приложение состоит из 2х экранов:
- Экран со списком жанров и фильмов соответствующих этим жанрам 
- Экран просмотра деталей о фильме

Экран со списком жанров и фильмов соответствующих этим жанрам:
- На экране представлен список жанров всех жанров для полученных фильмов. По клику на жанр он выделяется, а список фильмов фильтруется в соответствии с выбранным жанром.
Если нажать на выбранный жанр еще раз, то список обновится к состоянию не выбранного жанра - список всех доступных фильмов. По клику на фильм или его название происходит 
переход на экран деталей о фильме.

Экран просмотра деталей о фильме:
- На экране просмотра можно увидеть превью фильма, оригинальное название, локализироване название, дату выпуска, рейтинг и описание фильма.

Стек:
- Retrofit - работа с сетью
- Dagger Hilt - Di
- Navigation component - навигация по фрагментам
- Room - локальная бд
- Coroutine Flow - асинхронность
- Moxy - Mvp

Архитектура:
Использована Clean архитектура и принципы Solid, Single activity и MVP

Апк приложения расположена по пути app/build/outputs/apk/debug/
