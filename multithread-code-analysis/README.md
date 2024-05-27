## Анализ кода, содержащий элементы параллельного программирования

Рассматриваемый проект [Pizza-Store_Multithreading-Example](https://github.com/kwsthsve/Pizza-Store_Multithreading-Example/tree/main)
 представляет собой синхронизацию "обработки" заказов в заведении за счет распаралелливания потоков. Исходный код написан на C и не содержит элементы библиотеки OpenMP.

Запустим программу с следующими аргументами:
``` 
$ ./main 6 6
```

## ThreadSanitizer
Проект с использованием ThreadSanitizer запускается следующей командой:
``` 
$ gcc main.c -fsanitize=thread -o main
$ ./main 6 6
```

## Helgrind
Проект с использованием Helgrind запускается следующей командой:
``` 
$ gcc main.c -o main
$ valgrind --tool=helgrind ./main 6 6
```

## Итог
пока что нет