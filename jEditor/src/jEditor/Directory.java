package jEditor;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;


class Directory<T> implements Collector<T, StringBuilder, String> {
    private List<Directory> directories;
    private List<File> files;

    @Override
    public Supplier<StringBuilder> supplier() {
        return StringBuilder::new;
    }

    @Override
    public BiConsumer<StringBuilder, T> accumulator() {
        return (left, right) -> left.append("\n").append(right.toString());
    }

    @Override
    public BinaryOperator<StringBuilder> combiner() {
        return StringBuilder::append;
    }

    @Override
    public Function<StringBuilder, String> finisher() {
        return StringBuilder::toString;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return null;
    }

    Directory(String pathName) {
        File[] temp = new File(pathName).listFiles();
        if(temp != null) {
            Stream.of(temp).forEach((f) -> {
                if(f.isDirectory()) directories.add(new Directory(f.getAbsolutePath()));
                else files.add(f);
            });
        }
    }

    public String toString() {
        return directories.stream().map(Directory::toString).collect(Directory::supplier) +
                "\n" + files.stream().map(File::toString).collect();
    }

}

