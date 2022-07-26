# App Biblioteca de Jogos
Aplicativo desenvolvido para o Trabalho 1 da Disciplina de Desenvolvimento Móvel do Curso de Ciência da Computação da UFSCar

### Integrantes ###
- Lucas Vinícius Domingues 769699
- Rafael Yoshio Yamawaki Murata 769681
- Victor Luís Aguilar Antunes 769734

### Bibliotecas usadas
#### (App android nativo - Java)
- Retrofit
- Gson
- Scalars
- Picasso

### Para executar
Há duas formas de executar o aplicativo, pelo Android Studio ou pela linha de comando:
- Pelo emulador: No Android Studio, crie um Dispositivo Virtual Android (AVD) ou conecte pelo adb seu dispositivo físico, depois na barra de ferramentas, selecione o dispositivo em que você quer executar o app no menu suspenso, e após isso clique em Run
- Ou executando por linha de comando
```
./gradlew build
``` 
> Versão testada do Android Studio 2021.2.1

### Funcionamento
O app é composto por duas activities: 
- Tela inicial que contém 3 fragmentos:
  - Início, com recomendações de jogos,
  - Explorar, para pesquisar um jogo,
  - Coleções, que mostrará os jogos salvos;
- Tela de jogo, que contém as informações de um jogo como capa, nome, lançamento, gênero, resumo e plataformas

Os dados do app são obtidos através da API [IGDB](https://api-docs.igdb.com/#about)
