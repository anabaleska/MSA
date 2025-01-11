package backend.model.dto;
import lombok.NonNull;

public record LoginDTO(@NonNull String email,
                           @NonNull String password) {
}
