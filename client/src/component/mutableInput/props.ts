export type MutableInputProps = {
    text: string;
    setText: (value: string) => void;
    placeHolder: string;
    className: string;
    onChange: (text: string) => void
}
