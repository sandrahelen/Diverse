##
#Oblig 2
#
# %rdi = h
# %rsi = m
# %rdx = s


#def hms_to_sec(0, 1, 5) = (0h * 3600) + (1m * 60) + 5s = 65 s

	.globl hms_to_sec
# Omformer et klokkeslett (angitt i timer, minutter og sekunder) til
# antall sekunder.

hms_to_sec:

	imulq	$3600,%rdi			#0 h * 3600 = 0 s
	movq	%rdi,%rax				#move 0 to %rax
	imulq	$60,%rsi				#1 m * 60 = 60 s
	addq	%rsi,%rax				#60 + 0 = 60 s
	addq	%rdx,%rax				#5 s + 60 = 65 s
	ret



#def sec_to_h(65) = 0 h

	.globl	sec_to_h
# Gitt et tidspunkt (angitt som sekunder siden midnatt), finn timen.

sec_to_h:

	movq	%rdi,%rax			#move 65 s to %rax
	movq	$3600,%r8			#move 3600 to %r8
	cqo
  idivq %r8           #divide 65s : 3600 = 0 h
	ret



#def sec_to_s(65) = 5 s

	.globl	sec_to_s
# Gitt et tidspunkt (angitt som sekunder siden midnatt), finn sekundet.

sec_to_s:

	movq	%rdi,%rax			#move 65s to %rax
	movq	$60,%r10			#move 60 to %r10
	cqo
	idivq	%r10					#divide 65s : 60 = 1 m ---> 5s i rest
	movq	%rdx,%rax			#move 5s to %rax
	ret


#def sec_to_m(65) = 1 m

	.globl	sec_to_m
# Gitt et tidspunkt (angitt som sekunder siden midnatt), finn minuttet.

sec_to_m:

	movq	%rdi,%rax			#move 65 s to %rax
	movq	$60,%r9				#move 60 to %r9
	cqo
	idivq	%r9						#divide 65s : 60 = 1 m
	cqo
	idivq	%r9						#divide 1 m : 60 = 0 ---> 1 m i rest
	movq 	%rdx,%rax			#move 1 m to %rax
	ret
