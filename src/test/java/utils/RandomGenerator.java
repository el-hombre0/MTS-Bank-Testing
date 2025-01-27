package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class RandomGenerator {
    private final Random random;
    private final String[] maleNames;
    private final String[] maleLastnames;
    private final String[] maleMiddlenames;

    private final String[] femaleNames;
    private final String[] femaleLastnames;
    private final String[] femaleMiddlenames;

    private final String[] emailDomains;

    private boolean isMale;
    private String lastname;

    public RandomGenerator() {
        random = new Random();

        maleNames = new String[]{"Давид", "Платон", "Арсений", "Савелий", "Артём"};
        maleLastnames = new String[]{"Терехов", "Мещеряков", "Васильев", "Морозов", "Новиков"};
        maleMiddlenames = new String[]{"Михайлович", "Даниилович", "Максимович", "Егорович", "Кириллович"};

        femaleNames = new String[]{"Полина", "Анастасия", "Александра", "Анна", "Василиса"};
        femaleLastnames = new String[]{"Рябова", "Горбачева", "Беляева", "Николаева", "Петрова"};
        femaleMiddlenames = new String[]{"Дмитриевна", "Тимуровна", "Эминовна", "Кирилловна", "Ивановна"};

        emailDomains = new String[]{"google.com", "yandex.ru", "rambler.ru", "mail.ru", "inbox.com", "hotmail.com", "yahoo.com", "outlook.com", "live.com"};
    }

    /**
     * Генератор случайного номера телефона
     * @return Строка с номером телефона
     */
    public String generatePhoneNumber() {
        // Генерируем случайное число в диапазоне от 1000000000 до 9999999999
        long phoneNumber = 9000000000L + random.nextInt(1000000000);
        return String.valueOf(phoneNumber);
    }

    /**
     * Генератор случайной даты рождения клиента
     * @return Дата рождения
     */
    public String generateRandomBirthDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        // Устанавливаем минимальную дату (1 января 1970 года)
        Calendar calendar = Calendar.getInstance();
        calendar.set(1970, Calendar.JANUARY, 1);
        Date startDate = calendar.getTime();

        // Устанавливаем максимальную дату (31 декабря 2005 года)
        calendar.set(2005, Calendar.DECEMBER, 31);
        Date endDate = calendar.getTime();

        // Генерируем случайное время в диапазоне
        long randomTime = startDate.getTime() +
                (long) (random.nextDouble() * (endDate.getTime() - startDate.getTime()));

        // Создаем случайную дату и форматируем её
        Date randomDate = new Date(randomTime);
        return dateFormat.format(randomDate);
    }

    /**
     * Генератор пола клиента
     */
    public void chooseSex() {
        isMale = random.nextBoolean(); // Случайно выбираем пол: true - мужчина, false - женщина
    }

    /**
     * Генератор случайных ФИО
     * @return Строка ФИО
     */
    public String generateRandomFullName() {
        chooseSex();
        if (isMale) {
            // Генерация мужского полного имени
            lastname = maleLastnames[random.nextInt(maleLastnames.length)];
            String firstname = maleNames[random.nextInt(maleNames.length)];
            String middlename = maleMiddlenames[random.nextInt(maleMiddlenames.length)];
            return lastname + " " + firstname + " " + middlename;
        } else {
            // Генерация женского полного имени
            lastname = femaleLastnames[random.nextInt(femaleLastnames.length)];
            String firstname = femaleNames[random.nextInt(femaleNames.length)];
            String middlename = femaleMiddlenames[random.nextInt(femaleMiddlenames.length)];
            return lastname + " " + firstname + " " + middlename;
        }
    }

    /**
     * Генератор случайного email на основе фамилии
     * @return Адрес email
     */
    public String generateRandomEmail() {
        // Случайный выбор домена
        String domain = emailDomains[random.nextInt(emailDomains.length)];

        // Транслитерация фамилии
        String transliteratedLastname = transliterate(lastname);

        // Формирование адреса электронной почты
        return transliteratedLastname + "@" + domain;
    }


    /**
     * Транслитерация кириллической строки
     * @param text Текст для транслитерации
     * @return Текст латиницей
     */
    private String transliterate(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            switch (c) {
                case 'А':
                case 'а':
                    result.append("a");
                    break;
                case 'Б':
                case 'б':
                    result.append("b");
                    break;
                case 'В':
                case 'в':
                    result.append("v");
                    break;
                case 'Г':
                case 'г':
                    result.append("g");
                    break;
                case 'Д':
                case 'д':
                    result.append("d");
                    break;
                case 'Е':
                case 'е':
                case 'Ё':
                case 'ё':
                    result.append("e");
                    break;
                case 'Ж':
                case 'ж':
                    result.append("zh");
                    break;
                case 'З':
                case 'з':
                    result.append("z");
                    break;
                case 'И':
                case 'и':
                    result.append("i");
                    break;
                case 'Й':
                case 'й':
                    result.append("y");
                    break;
                case 'К':
                case 'к':
                    result.append("k");
                    break;
                case 'Л':
                case 'л':
                    result.append("l");
                    break;
                case 'М':
                case 'м':
                    result.append("m");
                    break;
                case 'Н':
                case 'н':
                    result.append("n");
                    break;
                case 'О':
                case 'о':
                    result.append("o");
                    break;
                case 'П':
                case 'п':
                    result.append("p");
                    break;
                case 'Р':
                case 'р':
                    result.append("r");
                    break;
                case 'С':
                case 'с':
                    result.append("s");
                    break;
                case 'Т':
                case 'т':
                    result.append("t");
                    break;
                case 'У':
                case 'у':
                    result.append("u");
                    break;
                case 'Ф':
                case 'ф':
                    result.append("f");
                    break;
                case 'Х':
                case 'х':
                    result.append("kh");
                    break;
                case 'Ц':
                case 'ц':
                    result.append("ts");
                    break;
                case 'Ч':
                case 'ч':
                    result.append("ch");
                    break;
                case 'Ш':
                case 'ш':
                    result.append("sh");
                    break;
                case 'Щ':
                case 'щ':
                    result.append("shch");
                    break;
                case 'Ъ':
                case 'ъ':
                    break; // Пропускаем твердый знак
                case 'Ы':
                case 'ы':
                    result.append("y");
                    break;
                case 'Ь':
                case 'ь':
                    break; // Пропускаем мягкий знак
                case 'Э':
                case 'э':
                    result.append("e");
                    break;
                case 'Ю':
                case 'ю':
                    result.append("yu");
                    break;
                case 'Я':
                case 'я':
                    result.append("ya");
                    break;
                default:
                    result.append(c); // Добавляем символ как есть, если он не кириллический
            }
        }
        return result.toString();
    }
}
