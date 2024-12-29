package com.ceva.core1.ch10.synchronization;

import java.util.*;
import java.util.concurrent.locks.*;

/**
 * cada objeto Bank posee su propio objeto ReentrantLock. Si dos hilos intenta acceder
 * al mismo objeto Bank, el lock sirve para serializar el acceso. Sin embargo si dos hilos
 * acceden a diferentes objetos Bank, cada hilo adquiere un diferente lock y ninguno de los
 * hilos esta bloqueado. Asi es como deberia ser porque, los hilos no pueden interferir entre si
 * cuando manipulan diferentes instancias de Bank
 */
public class Bank {
    // almacenamos saldo de cuentas
    private final double[] accounts;
    // objeto bankLock utilizado para sincronizar el acceso a las cuentas bancarias
    private final Lock bankLock = new ReentrantLock();
    // objeto Condition asociado al Lock
    private final Condition sufficientFunds = bankLock.newCondition();

    public Bank(int n, double initialBalance)
    {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
    }

    /**
     * Transfers money from one account to another.
     * @param from the account to transfer from
     * @param to the account to transfer to
     * @param amount the amount to transfer
     */
    public void transfer(int from, int to, double amount) throws InterruptedException
    {
        /**
         *  protegemos el siguiente bloque de codigo con un ReentrantLock
         *  Garantizamos que solo un hilo a la vez pueda ingresar a la seccion critica
         *  tan pronto como un hilo bloquea el objeto lock, ningun otro hilo puede
         *  pasar la declaracion lock. cuando otros hilos llaman a lock se desactivan hasta
         *  que el primer hilo desbloquee el objeto bloqueado
         *
         *  Supongamos q un hilo llama a transfer y se adelanta antes de que sea hecho.
         *  SUpongamos que un segundo hilo llama a transfer, el segundo hilo no puede
         *  adquirir el lock y es bloqueado en la llamada al metodo lock. El segundo hilo
         *  esta desactivado y debe esperar a que el primer hilo termine la ejecucion del
         *  metodo transfer. Cuando el primer hilo desbloquea el lock entonces el
         *  segundo hilo puede proceder.
         */
        bankLock.lock();
        // seccion critica
        try
        {
            while (accounts[from] < amount){
                // si la cuenta de origen no tiene suficiente saldo, el hilo que ejecuta este metodo
                // entra en estado de espera, utilizando wait. esto libera el bloqueo que permite
                // a otros hilos ejecutar este metodo y eventualmente agregar mas fondos en la cuenta
                sufficientFunds.await();
            }
            System.out.print(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d", amount, from, to);
            accounts[to] += amount;
            System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());

            // termina la transferencia de fondos, y los saldos de cuentas han cambiado
            // por lo que es buena practica llamar a signalAll, para que los hilos en espera
            // puedan inspeccionar el saldo de la cuenta y decidir continuar con la operaciones
            // de tranferencia.
            sufficientFunds.signalAll();
        }
        finally
        {
            // desbloqueo
            bankLock.unlock();
        }
    }

    /**
     * Gets the sum of all account balances.
     * el metodo esta sincronizado para garantizar la consistencia de los datos
     * @return the total balance
     */
    public double getTotalBalance()
    {
        bankLock.lock();
        try
        {
            double sum = 0;

            for (double a : accounts)
                sum += a;

            return sum;
        }
        finally
        {
            bankLock.unlock();
        }
    }

    /**
     * Gets the number of accounts in the bank.
     * @return the number of accounts
     */
    public int size()
    {
        return accounts.length;
    }
}
