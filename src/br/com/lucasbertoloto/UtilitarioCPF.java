package br.com.lucasbertoloto;

public class UtilitarioCPF {
    public static boolean validarCPF(String cpf) throws InvalidCPFException {
        cpf = pegarApenasNumeros(cpf);
        return verificarTamanhoDoCPF(cpf) && (!verificarDigitosRepetidos(cpf)) && verificarDigitosVerificadores(cpf);
    }

    public static String formatarCPFParaPontos(String cpf) throws InvalidCPFException {
        StringBuilder sb = new StringBuilder();
        cpf = pegarApenasNumeros(cpf);
        if (verificarTamanhoDoCPF(cpf)) {
            formatar(cpf, sb);
        }
        return sb.toString();
    }

    private static String pegarApenasNumeros(String cpf) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cpf.length(); i++) {
            char c = cpf.charAt(i);
            if (c > 47 && c < 58)
                sb.append(c);
        }
        return sb.toString();
    }

    private static boolean verificarTamanhoDoCPF(String cpf) throws InvalidCPFException {
        if (cpf.length() != 11)
            throw new InvalidCPFException("A quantidade de digitos do CPF esta incorreta!\nTamanho esperado: 11"
                    + "\nQuantidade de digitos do CPF fornecido: " + cpf.length()
                    + "\nCPF fornecido apenas com digitos: " + cpf);
        return true;
    }

    private static boolean verificarDigitosRepetidos(String cpf) throws InvalidCPFException {
        if (cpf.equals("00000000000") ||
                cpf.equals("11111111111") ||
                cpf.equals("22222222222") ||
                cpf.equals("33333333333") ||
                cpf.equals("44444444444") ||
                cpf.equals("55555555555") ||
                cpf.equals("66666666666") ||
                cpf.equals("77777777777") ||
                cpf.equals("88888888888") ||
                cpf.equals("99999999999"))
            throw new InvalidCPFException("Os digitos nao podem ser repetidos: "
                    + UtilitarioCPF.formatarCPFParaPontos(cpf));
        return false;
    }

    private static boolean verificarDigitosVerificadores(String cpf) throws InvalidCPFException {
        if (!(verificarPrimeiroDigitoVerificador(cpf) && verificarSegundoDigitoVerificador(cpf)))
            throw new InvalidCPFException("CPF fornecido nao e valido: "
                    + (UtilitarioCPF.formatarCPFParaPontos(cpf)));
        return true;
    }

    private static boolean verificarPrimeiroDigitoVerificador(String cpf) {
        int calculo = 0;
        char c;
        int digito;
        int peso = 10;
        for (int i = 0; i < 9; i++) {
            c = cpf.charAt(i);
            digito = (int) c - 48;
            calculo = calculo + (digito * peso);
            peso--;
        }
        int digitoEsperado = gerarDigitoEsperado(calculo);
        char digitoEsperadoChar = (char) (digitoEsperado + 48);
        return digitoEsperadoChar == cpf.charAt(9);
    }

    private static boolean verificarSegundoDigitoVerificador(String cpf) {
        int calculo = 0;
        char c;
        int digito;
        int peso = 11;
        for (int i = 0; i < 10; i++) {
            c = cpf.charAt(i);
            digito = (int) c - 48;
            calculo = calculo + (digito * peso);
            peso--;
        }
        int digitoEsperado = gerarDigitoEsperado(calculo);
        char digitoEsperadoChar = (char) (digitoEsperado + 48);
        return digitoEsperadoChar == cpf.charAt(10);
    }

    private static int gerarDigitoEsperado(int calculo) {
        int digitoEsperado = 11 - (calculo % 11);
        if (digitoEsperado >= 10)
            digitoEsperado = 0;
        return digitoEsperado;
    }

    private static void formatar(String cpf, StringBuilder sb) {
        sb.append(cpf, 0, 3);
        sb.append(".");
        sb.append(cpf, 3, 6);
        sb.append(".");
        sb.append(cpf, 6, 9);
        sb.append("-");
        sb.append(cpf, 9, 11);
    }
}