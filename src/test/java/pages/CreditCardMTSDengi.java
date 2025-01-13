package pages;

import com.codeborne.selenide.SelenideElement;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class CreditCardMTSDengi {
    private final SelenideElement phoneNumberInput = $x("//input[@name='phoneNumber']");
    private final SelenideElement clientNameInput = $x("//textarea[@name='clientFio']");
    private final SelenideElement birthDateInput = $x("//input[@name='birthDate']");
    private final SelenideElement emailInput = $x("//input[@name='email']");
    private final SelenideElement nextButton = $x("//div[contains(text(), 'Далее')]");
    private final SelenideElement confirmationPhoneNumberText = $x("//h4[contains(text(), 'Подтвердите номер телефона')]");

    private final String[] male_names = {"Давид", "Платон", "Арсений", "Савелий", "Артём"};
    private final String[] male_lastnames = {"Терехов", "Мещеряков", "Васильев", "Морозов", "Новиков"};
    private final String[] male_middlenames = {"Михайлович", "Даниилович", "Максимович", "Егорович", "Кириллович"};

    private final String[] female_names = {"Полина", "Анастасия", "Александра", "Анна", "Василиса"};
    private final String[] female_lastnames = {"Рябова", "Горбачева", "Беляева", "Николаева", "Петрова"};
    private final String[] female_middlenames = {"Дмитриевна", "Тимуровна", "Эминовна", "Кирилловна", "Ивановна"};

    private final String[] emailDomains = {"google.com", "yandex.ru", "rambler.ru", "mail.ru", "inbox.com", "hotmail.com", "yahoo.com", "outlook.com", "live.com"};
    private boolean isMale;
    private String lastname;

    public void enterPhoneNumberInput(String phone) {
        phoneNumberInput.val(phone);
    }

    public String generatePhoneNumber() {
        Random random = new Random();
        // Генерируем случайное число в диапазоне от 1000000000 до 9999999999
        long phoneNumber = 9000000000L + random.nextInt(1000000000);
        return String.valueOf(phoneNumber);
    }

    public void enterBirthDateInput(String birthDate) {
        birthDateInput.val(birthDate);
    }

    public String generateRandomBirthDate() {
        Random random = new Random();
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

    public void chooseSex() {
        Random random = new Random();
        this.isMale = random.nextBoolean(); // Случайно выбираем пол: true - мужчина, false - женщина
    }

    public String generateRandomFullName() {
        chooseSex();
        Random random = new Random();
        if (isMale) {
            // Генерация мужского полного имени
            lastname = male_lastnames[random.nextInt(male_lastnames.length)];
            String firstname = male_names[random.nextInt(male_names.length)];
            String middlename = male_middlenames[random.nextInt(male_middlenames.length)];
            return lastname + " " + firstname + " " + middlename;
        } else {
            // Генерация женского полного имени
            lastname = female_lastnames[random.nextInt(female_lastnames.length)];
            String firstname = female_names[random.nextInt(female_names.length)];
            String middlename = female_middlenames[random.nextInt(female_middlenames.length)];
            return lastname + " " + firstname + " " + middlename;
        }
    }

    public void enterClientName(String name) {
        clientNameInput.val(name);
    }

    public String generateRandomEmail() {
        Random random = new Random();

        // Случайный выбор домена
        String domain = emailDomains[random.nextInt(emailDomains.length)];

        // Транслитерация фамилии
        String transliteratedLastname = transliterate(lastname);

        // Формирование адреса электронной почты
        return transliteratedLastname + "@" + domain;
    }

    // Метод для транслитерации кириллической строки
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

    public void enterEmail(String email) {
        emailInput.val(email);
    }

    public void pressNextButton() {
        nextButton.scrollTo();
        nextButton.click();
    }

    public void checkFormConfirmation() {
        confirmationPhoneNumberText.shouldBe(visible);
    }
}
