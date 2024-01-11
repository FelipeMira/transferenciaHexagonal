package conta.api.domain.pagiable;

import conta.api.domain.response.ContaResponse;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@ApiModel
@Getter
@Setter
public class PageContaResponse extends PageResponse<ContaResponse> implements Serializable {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public PageContaResponse(PageResponse p) {
        super(p.getNumber(), p.size, p.totalPages, p.numberOfElements, p.totalElements, p.hasContent, p.first, p.last, p.nextPage, p.previousPage, p.content);
    }

}
