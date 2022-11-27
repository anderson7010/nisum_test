package co.com.nisum.usecase.user;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DeleteUserUseCase {
    private final UpdateUserUseCase updateUserUseCase;

    public void deleteUser(UUID id) {
        updateUserUseCase.updateUserState(id, Boolean.FALSE);
    }
}
