# Existence Ontology: решение "экзистенциальных" вопросов

**[English version](readme.md)**

[![core version](https://img.shields.io/maven-central/v/team.isaz.existence/core?label=core)](https://central.sonatype.com/artifact/team.isaz.existence/core)
[![starter version](https://img.shields.io/maven-central/v/team.isaz.existence/starter?label=starter)](https://central.sonatype.com/artifact/team.isaz.existence/starter)

## О проекте

Иногда я смотрю на код и думаю: а существует ли вообще этот объект? И что вообще значит "существует"? Если переменная
равна `null`, то она, конечно, не может считаться существующей. Если это `Optional`, то можно спросить `o.isPresent()` и
получить ответ. Но что если внутри ещё один `Optional`? А если строка состоит только из пробелов?
Метод `"     ".isEmpty()` считает, что она не пуста — но ведь полезной информации в ней ноль.
То же самое происходит и с бизнес-объектами, которые можно считать "пустыми", если обязательные поля формы не заполнены.

Existence Ontology стремится решить эти проблемы, предлагая систему правил и удобные интерфейсы для их использования.
Любые конструктивные предложения, высказанные в доброжелательном тоне, приветствуются.

## Компоненты

- `core`
    - **Описание**: Базовая библиотека на Java 8 без сторонних зависимостей (только для тестов). Позволяет создавать
      `ExistenceChecker`, способный выполнять все проверки, которые нам интересны. Включает готовую реализацию
      проверяющего и несколько полезных правил.
    - **Статус**: `PUBLISHED`
- `lombok-utils`
    - **Описание**: Утилитная библиотека с классом `ExistenceUtils`, позволяющим выполнять проверки без создания
      `ExistenceChecker`. Также предлагает `@ExtensionMethod` (спасибо Lombok!) для класса `Object`.
    - **Статус**: `CONSIDERING`
    - **Обоснование**: `ExistenceUtils` должен содержать статическое поле с экземпляром `ExistenceChecker`. Но какой в
      этом толк, если нельзя добавлять свои правила? Модифицировать глобальную переменную откуда угодно — небезопасно.
      Хотя это очень удобно...
- `spring-starter`
    - **Описание**: Классический стартер, предоставляющий предварительно настроенные `ExistenceChecker`-бины, а также
      возможность сканировать и регистрировать правила по пакетам и списку имён. Spring Boot 3, Java 21.
    - **Статус**: `PUBLISHED`

---

# Правила ветвления и версионирования

Проект использует ручное версионирование в соответствии с [**SemVer (X.Y.Z)**](https://semver.org/lang/ru/):

- **X** — major: несовместимые изменения
- **Y** — minor: новые возможности без нарушения обратной совместимости
- **Z** — patch: исправления багов и документации без изменения контракта

## Основные ветки

- **`master`** — содержит только **стабильные опубликованные версии**. Коммиты попадают в `master`:
    - либо через merge из `rc/*`,
    - либо через merge из `fix/*`.

- **`rc/module-x.y.0`** — ветка релиз-кандидата. Создаётся от `master` и собирает новые фичи для конкретного модуля.
  Версия обновляется **только здесь**. После стабилизации и финального тестирования мёрджится в `master` и тегируется.

- **`feature/*`** — рабочие ветки для новых фич. Ответвляются от `rc/module`. Версия не трогается. После завершения
  создаётся Merge Request в соответствующий `rc/module`.

- **`fix/module-x.y.z-*`** — быстрые багфиксы. Ответвляются от `master` и поднимают Z-часть версии. Текст после тире
  описывает изменения, например: `fix/core-1.5.0-nullpointer_in_static_beans`. После завершения создаётся Merge Request
  в `master`, и версия поднимается вручную.

## Пример процесса релиза

1. Сразу после мёрджа предыдущего релиза `core` создаётся новая ветка `rc/core` от `master`.
2. Все фичи разрабатываются в `feature/*` и мёрджатся в `rc/core` через Merge Request.
3. Версия устанавливается и обновляется вручную **в ветке `rc/core`** ближе к финалу. При необходимости (например, при
   срочном breaking change) номер версии и имя ветки можно скорректировать.
4. Когда ветка готова:
    - ничего больше не мёрджится,
    - она мёрджится в `master`,
    - коммит тегируется как `core-v1.5.0`,
    - происходит публикация (если надо) по тегу.

## Пример процесса hotfix

1. Создаётся ветка `fix/core-1.5.1-some_bug` от `master`.
2. Вносится исправление.
3. Версия поднимается до `1.5.1`.
4. Ветка мёрджится в `master`, создаётся тег `core-v1.5.1`.

## Общие правила

- **Версии не трогаются в `feature/*` ветках**.
- Версии изменяются **только в `rc/*` и `fix/*`**.
- Публикация происходит **только из `master` по тегу**, вручную или через CI.
- Каждый тег `module-vX.Y.Z` указывает на коммит с соответствующей версией в коде.
- Каждый Merge Request должен содержать описание изменений на русском или английском языке в формате markdown.
- При мёрдже используется Fast Forward. Коммиты сквошатся, заголовок — имя ветки, тело — описание из MR.
