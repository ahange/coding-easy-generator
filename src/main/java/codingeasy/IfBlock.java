package codingeasy;

import java.util.Objects;

public class IfBlock extends TestBlock<IfBlock> {

	private CodeBlock<?> elseBlock;

	protected IfBlock() {
		super("if");
	}

	public IfBlock _else(CodeBlock<?> code) {
		elseBlock = code;
		return this;
	}
	
	@Override
	public IfBlock code(CodeBlock<?> code) {
		return code(code.getCode());
	}
	
	@Override
	public void print(CodePrinter code) {
		super.print(code);
		
		if (Objects.nonNull(elseBlock)) {
			code.space();
			code.append("else");
			if (elseBlock instanceof IfBlock) {
				code.space();
			}
			code.append(elseBlock.build());
		}
	}
	
}