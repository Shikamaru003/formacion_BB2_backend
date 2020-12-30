package formacion.bb2.utils;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class DTOModelMapper {

    private DTOModelMapper() {
        super();
    }

    private static ModelMapper modelMapper = new ModelMapper();

    public static <T> T map(Object source, Class<T> targetClass) {
        return modelMapper.map(source, targetClass);
    }

    public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
}
