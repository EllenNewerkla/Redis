package projeto.demo;

import redis.clients.jedis.Jedis;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Jedis jedis = new Jedis("redis://default:JEyBxMIh8PSxSYs5P9BA4H7hom5G10Ce@redis-18408.c10.us-east-1-4.ec2.cloud.redislabs.com:18408");


		//ADCIONANDO TAREFAS
	jedis.hset("Tarefa", "0", "Limpar Casa");
	jedis.hset("Tarefa", "1", "Entregar Trabalho");
	jedis.hset("Tarefa", "2", "Comparecer a Reunião");
	jedis.hset("Tarefa", "3", "Fazer Compras");
	System.out.println("Tarefas adicionadas com sucesso!");

		//LISTANDO TAREFAS
	System.out.println("\nSuas Tarefas: ");
	jedis.hgetAll("Tarefa").forEach((key, value) -> System.out.println(key + " -- " + value));

		//TAREFA CONCLUIDA
	jedis.hset("Tarefa-Concluìda", "3", jedis.hget("Tarefa", "3"));
	jedis.hdel("Tarefa", "3");
	System.out.println("\nTarefa 3 está Concluìda!");

		//REMOVENDO TAREFA
	jedis.hdel("Tarefa", "0");
	System.out.println("\nTarefa 0 foi removida da Lista");

	jedis.close();
	}
}
