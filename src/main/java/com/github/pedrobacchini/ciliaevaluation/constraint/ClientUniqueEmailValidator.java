package com.github.pedrobacchini.ciliaevaluation.constraint;

import com.github.pedrobacchini.ciliaevaluation.config.LocaleMessageSource;
import com.github.pedrobacchini.ciliaevaluation.dto.ClientDTO;
import com.github.pedrobacchini.ciliaevaluation.entity.Client;
import com.github.pedrobacchini.ciliaevaluation.exception.EmailAlreadyUsedException;
import com.github.pedrobacchini.ciliaevaluation.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class ClientUniqueEmailValidator implements ConstraintValidator<ClientUniqueEmail, ClientDTO> {

    private final HttpServletRequest request;
    private final ClientRepository clientRepository;
    private final LocaleMessageSource localeMessageSource;

    @SuppressWarnings("rawtypes")
    @Override
    public boolean isValid(ClientDTO clientDTO, ConstraintValidatorContext context) {
        Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Optional<Client> clientOptional = clientRepository.findByEmail(clientDTO.getEmail());

//        Updated Client
        if (pathVariables.containsKey("uuid")) {
            UUID uriUuid = UUID.fromString((String) pathVariables.get("uuid"));
            clientOptional.ifPresent(client -> {
                if (!client.getUuid().equals(uriUuid))
                    throw new EmailAlreadyUsedException(localeMessageSource
                            .getMessage("object-already-used-email", clientDTO.getEmail()));
            });
//        New Client
        } else {
            clientOptional.ifPresent(found -> {
                throw new EmailAlreadyUsedException(localeMessageSource
                        .getMessage("object-already-used-email", found.getEmail()));
            });
        }
        return true;
    }
}
